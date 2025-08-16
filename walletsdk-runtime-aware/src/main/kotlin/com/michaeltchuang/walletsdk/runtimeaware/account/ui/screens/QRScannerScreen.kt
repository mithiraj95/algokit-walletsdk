package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.AlgoKitScreens
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.components.PermissionRationaleDialog
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.QRScannerViewModel
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.utils.navigateWithArgument
import org.koin.androidx.compose.koinViewModel
import qrscanner.CameraLens
import qrscanner.QrScanner

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QRCodeScannerScreen(
    navController: NavController,
    onQrScanned: (String) -> Unit
) {
    val viewModel: QRScannerViewModel = koinViewModel()
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val hasProcessedResult = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect {
            when (it) {
                is QRScannerViewModel.ViewEvent.NavigateToRecoveryPhraseScreen -> {
                    navController.navigate(AlgoKitScreens.RECOVER_PHRASE_SCREEN.name + "/${it.mnemonic}")
                }

                is QRScannerViewModel.ViewEvent.NavigateToTransactionSignatureRequestScreen -> {
                    navController.navigateWithArgument(
                        AlgoKitScreens.TRANSACTION_SIGNATURE_SCREEN.name,
                        it.keyReg
                    )
                }

                is QRScannerViewModel.ViewEvent.ShowUnrecognizedDeeplink -> {
                    onQrScanned("Unrecognized QR Code")
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        when {
            cameraPermissionState.status.isGranted -> {
                QrScanner(
                    modifier = Modifier.fillMaxSize(),
                    flashlightOn = false,
                    cameraLens = CameraLens.Back,
                    openImagePicker = false,
                    onCompletion = {
                        if (!hasProcessedResult.value) {
                            viewModel.handleDeeplink(it)
                            hasProcessedResult.value = true
                        }
                    },
                    imagePickerHandler = {},
                    onFailure = {},
                )
            }

            cameraPermissionState.status.shouldShowRationale || !cameraPermissionState.status.isGranted -> {
                PermissionRationaleDialog(
                    onRequestPermission = { cameraPermissionState.launchPermissionRequest() },
                    onDismiss = { navController.popBackStack() }
                )
            }
        }
        AlgoKitTopBar(
            modifier = Modifier.padding(horizontal = 24.dp),
            onClick = { navController.popBackStack() }
        )
    }
}

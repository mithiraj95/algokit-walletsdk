package com.michaeltchuang.walletsdk.runtimeaware.account.ui.components

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.AccountRecoveryTypeSelectionScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.CreateAccountNameScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.CreateAccountTypeScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.HdWalletSelectionScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.QRCodeScannerScreen
import kotlinx.coroutines.launch

enum class AlgoKitEvent {
    ClOSE_BOTTOMSHEET, ALGO25_ACCOUNT_CREATED, HD_ACCOUNT_CREATED
}

enum class OnBoardingScreens() {
    CREATE_ACCOUNT_TYPE,
    CREATE_ACCOUNT_NAME,
    HD_WALLET_SELECTION_SCREEN,
    ACCOUNT_RECOVERY_TYPE_SCREEN,
    QR_CODE_SCANNER_SCREEN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingBottomSheet(
    showSheet: Boolean,
    onAlgoKitEvent: (event: AlgoKitEvent) -> Unit
) {
    if (showSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = {
                onAlgoKitEvent(AlgoKitEvent.ClOSE_BOTTOMSHEET)
            }, sheetState = sheetState, dragHandle = null
        ) {
            OnBoardingBottomSheetNavHost {
                onAlgoKitEvent(AlgoKitEvent.ALGO25_ACCOUNT_CREATED)
            }
        }
    }
}

@Composable
fun OnBoardingBottomSheetNavHost(
    navController: NavHostController = rememberNavController(),
    onFinish: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxHeight(.9f),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .padding(bottom = 64.dp)
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(0.dp)
        ) {
            NavHost(
                navController,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                startDestination = OnBoardingScreens.CREATE_ACCOUNT_TYPE.name) {
                composable(OnBoardingScreens.CREATE_ACCOUNT_TYPE.name) {
                    CreateAccountTypeScreen(navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable(OnBoardingScreens.CREATE_ACCOUNT_NAME.name) {
                    CreateAccountNameScreen(
                        navController, {
                            onFinish()
                        })
                }
                composable(OnBoardingScreens.HD_WALLET_SELECTION_SCREEN.name) {
                    HdWalletSelectionScreen(navController = navController)
                }

                composable(OnBoardingScreens.ACCOUNT_RECOVERY_TYPE_SCREEN.name) {
                    AccountRecoveryTypeSelectionScreen(navController = navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable (OnBoardingScreens.QR_CODE_SCANNER_SCREEN.name){
                    QRCodeScannerScreen(navController = navController){
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
            }
        }
    }
}



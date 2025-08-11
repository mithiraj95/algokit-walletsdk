package com.michaeltchuang.walletsdk.runtimeaware.account.ui.components

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
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
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.AlgoKitWebViewPlatformScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.CreateAccountNameScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.CreateAccountTypeScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.HdWalletSelectionScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.QRCodeScannerScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.RecoverAnAccountScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.RecoveryPhraseScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.TransactionSignatureRequestScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.TransactionSuccessScreen
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants.REPO_URL
import com.michaeltchuang.walletsdk.runtimeaware.utils.getData
import kotlinx.coroutines.launch

enum class AlgoKitEvent {
    ClOSE_BOTTOMSHEET, ALGO25_ACCOUNT_CREATED, HD_ACCOUNT_CREATED
}

enum class OnBoardingScreens() {
    CREATE_ACCOUNT_TYPE, CREATE_ACCOUNT_NAME,
    HD_WALLET_SELECTION_SCREEN,
    ACCOUNT_RECOVERY_TYPE_SCREEN,
    QR_CODE_SCANNER_SCREEN,
    RECOVER_PHRASE_SCREEN,
    RECOVER_AN_ACCOUNT_SCREEN,
    ALGOKIT_WEBVIEW_PLATFORM_SCREEN,
    TRANSACTION_SIGNATURE_SCREEN,
    TRANSACTION_SUCCESS_SCREEN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingBottomSheet(
    showSheet: Boolean, qrScanFlow: Boolean = false, onAlgoKitEvent: (event: AlgoKitEvent) -> Unit
) {
    if (showSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = {
                onAlgoKitEvent(AlgoKitEvent.ClOSE_BOTTOMSHEET)
            }, sheetState = sheetState, dragHandle = null
        ) {
            OnBoardingBottomSheetNavHost(
                startDestination = startDestination(qrScanFlow),
                closeSheet = {
                    onAlgoKitEvent(AlgoKitEvent.ClOSE_BOTTOMSHEET)
                }) {
                onAlgoKitEvent(AlgoKitEvent.ALGO25_ACCOUNT_CREATED)
            }
        }
    }
}

@Composable
fun OnBoardingBottomSheetNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = OnBoardingScreens.CREATE_ACCOUNT_TYPE.name,
    closeSheet: () -> Unit,
    onFinish: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxHeight(.9f), snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState, modifier = Modifier.padding(bottom = 64.dp)
            )
        }) { padding ->
        Box(
            modifier = Modifier
                .background(color = AlgoKitTheme.colors.background)
                .padding(0.dp)
        ) {
            NavHost(
                navController,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                startDestination = startDestination
            ) {
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
                composable(OnBoardingScreens.QR_CODE_SCANNER_SCREEN.name) {
                    QRCodeScannerScreen(navController = navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable(route = OnBoardingScreens.RECOVER_PHRASE_SCREEN.name + "/{mnemonic}") { it ->
                    it.arguments?.getString("mnemonic")?.let {
                        RecoveryPhraseScreen(navController = navController, it) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    }
                }
                composable(route = OnBoardingScreens.RECOVER_AN_ACCOUNT_SCREEN.name) {
                    RecoverAnAccountScreen(navController = navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable(route = OnBoardingScreens.ALGOKIT_WEBVIEW_PLATFORM_SCREEN.name) {
                    AlgoKitWebViewPlatformScreen(url = REPO_URL)
                }

                composable(route = OnBoardingScreens.TRANSACTION_SIGNATURE_SCREEN.name) {
                    navController.getData<DeepLink.KeyReg>()?.let {
                        TransactionSignatureRequestScreen(navController = navController, it)
                    }
                }
                composable(route = OnBoardingScreens.TRANSACTION_SUCCESS_SCREEN.name) {
                    TransactionSuccessScreen {
                        closeSheet()
                    }
                }
            }
        }
    }
}

fun startDestination(qrScanFlow: Boolean): String {
    return if (qrScanFlow) {
        OnBoardingScreens.QR_CODE_SCANNER_SCREEN.name
    } else {
        OnBoardingScreens.CREATE_ACCOUNT_TYPE.name
    }
}

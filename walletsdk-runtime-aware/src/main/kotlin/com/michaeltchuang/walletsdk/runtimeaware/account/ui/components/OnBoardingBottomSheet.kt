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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.AccountRecoveryTypeSelectionScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.AlgoKitWebViewPlatformScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.CreateAccountNameScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.CreateAccountTypeScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.HdWalletSelectionScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.InitialRegisterIntroScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.QRCodeScannerScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.RecoverAnAccountScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.RecoveryPhraseScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.SettingsScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.ThemeScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.TransactionSignatureRequestScreen
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.TransactionSuccessScreen
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants.REPO_URL
import com.michaeltchuang.walletsdk.runtimeaware.utils.getData
import com.michaeltchuang.walletsdk.runtimeaware.utils.getSavedThemePreferenceFlow
import kotlinx.coroutines.launch

enum class AlgoKitEvent {
    ALGO25_ACCOUNT_CREATED,
    ClOSE_BOTTOMSHEET,
    HD_ACCOUNT_CREATED
}

enum class AlgoKitScreens() {
    ACCOUNT_RECOVERY_TYPE_SCREEN,
    CREATE_ACCOUNT_NAME,
    CREATE_ACCOUNT_TYPE,
    HD_WALLET_SELECTION_SCREEN,
    INITIAL_REGISTER_INTRO_SCREEN,
    QR_CODE_SCANNER_SCREEN,
    RECOVER_AN_ACCOUNT_SCREEN,
    RECOVER_PHRASE_SCREEN,
    SETTINGS_SCREEN,
    THEME_SCREEN,
    TRANSACTION_SIGNATURE_SCREEN,
    TRANSACTION_SUCCESS_SCREEN,
    WEBVIEW_PLATFORM_SCREEN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingBottomSheet(
    showSheet: Boolean,
    accounts: Int,
    launchQRScanScreen: Boolean = false,
    launchSettingsScreen: Boolean = false,
    onAlgoKitEvent: (event: AlgoKitEvent) -> Unit
) {
    if (showSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = {
                onAlgoKitEvent(AlgoKitEvent.ClOSE_BOTTOMSHEET)
            }, sheetState = sheetState, dragHandle = null
        ) {
            OnBoardingBottomSheetNavHost(
                startDestination = startDestination(
                    accounts, launchQRScanScreen, launchSettingsScreen
                ), closeSheet = {
                    onAlgoKitEvent(AlgoKitEvent.ClOSE_BOTTOMSHEET)
                }) {
                onAlgoKitEvent(AlgoKitEvent.ALGO25_ACCOUNT_CREATED)
            }
        }
    }
}

@Composable
fun OnBoardingBottomSheetNavHost(
    startDestination: String = AlgoKitScreens.CREATE_ACCOUNT_TYPE.name,
    closeSheet: () -> Unit,
    onFinish: () -> Unit,
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    var lastThemePrefKey by rememberSaveable { mutableStateOf<String?>(null) }
    val themePrefFlow = remember { getSavedThemePreferenceFlow(context) }

    LaunchedEffect(themePrefFlow) {
        themePrefFlow.collect { pref ->
            val currentKey = pref.name
            if (lastThemePrefKey != null && lastThemePrefKey != currentKey) {
                navController.popBackStack()
            }
            lastThemePrefKey = currentKey
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxHeight(.9f)
    ) { padding ->
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
                composable(AlgoKitScreens.CREATE_ACCOUNT_TYPE.name) {
                    CreateAccountTypeScreen(navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable(AlgoKitScreens.CREATE_ACCOUNT_NAME.name) {
                    CreateAccountNameScreen(
                        navController, {
                            onFinish()
                        })
                }
                composable(AlgoKitScreens.HD_WALLET_SELECTION_SCREEN.name) {
                    HdWalletSelectionScreen(navController = navController)
                }

                composable(AlgoKitScreens.ACCOUNT_RECOVERY_TYPE_SCREEN.name) {
                    AccountRecoveryTypeSelectionScreen(navController = navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable(AlgoKitScreens.QR_CODE_SCANNER_SCREEN.name) {
                    QRCodeScannerScreen(navController = navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable(route = AlgoKitScreens.RECOVER_PHRASE_SCREEN.name + "/{mnemonic}") { it ->
                    it.arguments?.getString("mnemonic")?.let {
                        RecoveryPhraseScreen(navController = navController, it) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    }
                }
                composable(route = AlgoKitScreens.RECOVER_AN_ACCOUNT_SCREEN.name) {
                    RecoverAnAccountScreen(navController = navController) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(it)
                        }
                    }
                }
                composable(route = AlgoKitScreens.WEBVIEW_PLATFORM_SCREEN.name) {
                    AlgoKitWebViewPlatformScreen(url = REPO_URL)
                }

                composable(route = AlgoKitScreens.TRANSACTION_SIGNATURE_SCREEN.name) {
                    navController.getData<DeepLink.KeyReg>()?.let {
                        TransactionSignatureRequestScreen(navController = navController, it)
                    }
                }
                composable(route = AlgoKitScreens.TRANSACTION_SUCCESS_SCREEN.name) {
                    TransactionSuccessScreen {
                        closeSheet()
                    }
                }
                composable(route = AlgoKitScreens.INITIAL_REGISTER_INTRO_SCREEN.name) {
                    InitialRegisterIntroScreen(navController)
                }
                composable(route = AlgoKitScreens.SETTINGS_SCREEN.name) {
                    SettingsScreen(navController)
                }
                composable(route = AlgoKitScreens.THEME_SCREEN.name) {
                    ThemeScreen(navController)
                }
            }
        }
    }
}

fun startDestination(accounts: Int, qrScanFlow: Boolean, launchSettingsScreen: Boolean): String {
    return when {
        launchSettingsScreen -> AlgoKitScreens.SETTINGS_SCREEN.name
        qrScanFlow -> AlgoKitScreens.QR_CODE_SCANNER_SCREEN.name
        accounts == 0 -> AlgoKitScreens.INITIAL_REGISTER_INTRO_SCREEN.name
        else -> AlgoKitScreens.CREATE_ACCOUNT_TYPE.name
    }
}

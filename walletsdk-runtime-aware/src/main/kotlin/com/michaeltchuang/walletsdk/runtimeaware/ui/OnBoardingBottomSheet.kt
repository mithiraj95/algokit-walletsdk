package com.michaeltchuang.walletsdk.runtimeaware.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class AlgoKitEvent {
    ClOSE_BOTTOMSHEET, ALGO25_ACCOUNT_CREATED, HD_ACCOUNT_CREATED
}

enum class OnBoardingScreens() {
    CREATE_ACCOUNT_TYPE,
    CREATE_ACCOUNT_NAME
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
    NavHost(navController, startDestination = OnBoardingScreens.CREATE_ACCOUNT_TYPE.name) {
        composable(OnBoardingScreens.CREATE_ACCOUNT_TYPE.name) {
            CreateAccountTypeScreen(navController)
        }
        composable(OnBoardingScreens.CREATE_ACCOUNT_NAME.name) {
            CreateAccountNameScreen(
                navController, {
                    onFinish()
                })
        }
    }
}



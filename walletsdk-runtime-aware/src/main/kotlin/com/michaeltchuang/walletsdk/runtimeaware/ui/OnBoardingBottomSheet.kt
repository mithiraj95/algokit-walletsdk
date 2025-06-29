package com.michaeltchuang.walletsdk.runtimeaware.ui

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import kotlinx.coroutines.launch

enum class AlgoKitEvent {
    ClOSE_BOTTOMSHEET, ALGO25_ACCOUNT_CREATED, HD_ACCOUNT_CREATED
}

enum class OnBoardingScreen {
    REGISTER_TYPE_SELECTION, CREATE_ACCOUNT_NAME
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingBottomSheet(
    runtimeAwareSdk: RuntimeAwareSdk, onAlgoKitEvent: (event: AlgoKitEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var onBoardingScreen by remember { mutableStateOf(OnBoardingScreen.REGISTER_TYPE_SELECTION) }
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            onAlgoKitEvent(AlgoKitEvent.ClOSE_BOTTOMSHEET)
        }, sheetState = sheetState, dragHandle = null
    ) {
        when (onBoardingScreen) {
            OnBoardingScreen.REGISTER_TYPE_SELECTION -> {
                RegisterTypeSelectionScreen {
                    if (it == AlgoKitEvent.ALGO25_ACCOUNT_CREATED) {
                        scope.launch {
                            //Algo25Account created
                            if (runtimeAwareSdk.createAlgo25Account() != null) {
                                onBoardingScreen = OnBoardingScreen.CREATE_ACCOUNT_NAME
                            } else {
                                Log.d("AlgoKitAccount", "Account not created")
                            }
                        }
                    }
                }
            }

            OnBoardingScreen.CREATE_ACCOUNT_NAME -> {
                CreateAccountNameScreen(onFinishClick = {
                    onAlgoKitEvent(AlgoKitEvent.ALGO25_ACCOUNT_CREATED)
                }, onBackClick = {
                    onBoardingScreen = OnBoardingScreen.REGISTER_TYPE_SELECTION
                })
            }
        }
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.wallet.ui

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
    UNKNOWN, ClOSE, ACCOUNT_CREATED, FINISH_ACCOUNT_CREATION
}

enum class OnBoardingScreen {
    REGISTER_TYPE_SELECTION, CREATE_ACCOUNT_NAME
}

private const val SAMPLE_HD_MNEMONIC =
    "borrow among leopard smooth trade cake profit proud matrix bottom goose charge oxygen shine punch hotel era monitor fossil violin tip notice any visit"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingBottomSheet(
    runtimeAwareSdk: RuntimeAwareSdk, algoKitEvent: (event: AlgoKitEvent) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var onBoardingScreen by remember { mutableStateOf(OnBoardingScreen.REGISTER_TYPE_SELECTION) }
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            algoKitEvent(AlgoKitEvent.ClOSE)
        }, sheetState = sheetState, dragHandle = null
    ) {
        when (onBoardingScreen) {
            OnBoardingScreen.REGISTER_TYPE_SELECTION -> {
                RegisterTypeSelectionScreen {
                    if (it == AlgoKitEvent.ACCOUNT_CREATED) {
                        scope.launch {
                            //Algo25Account created
                            if (runtimeAwareSdk.createAlgo25Account() != null) {
                                algoKitEvent(AlgoKitEvent.ACCOUNT_CREATED)
                                onBoardingScreen = OnBoardingScreen.CREATE_ACCOUNT_NAME
                            } else {
                                Log.d("AlgoKitAccount", "Account not created")
                            }
                        }
                    }
                }
            }

            OnBoardingScreen.CREATE_ACCOUNT_NAME -> {
                CreateAccountNameScreen(onFinishClick = {}, onBackClick = {})
            }
        }
    }
}

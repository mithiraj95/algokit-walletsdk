package com.michaeltchuang.walletsdk.runtimeaware.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk

@Composable
fun AccountScreen(runtimeAwareSdk: RuntimeAwareSdk) {
    var showSheet by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Button(onClick = {
                showSheet = true
            }) {
                Text(text = "Open AlgoKit Onboarding Bottomsheet")
            }
        }
    }

    if (showSheet) {
        OnBoardingBottomSheet(runtimeAwareSdk) {
            when (it) {
                AlgoKitEvent.ClOSE_BOTTOMSHEET -> {
                    showSheet = false
                }
                AlgoKitEvent.ALGO25_ACCOUNT_CREATED,
                AlgoKitEvent.HD_ACCOUNT_CREATED -> {
                    // TODO: Show Confetti
                }
            }
        }
    }
}
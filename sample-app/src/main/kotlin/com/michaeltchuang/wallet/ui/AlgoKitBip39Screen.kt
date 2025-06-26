package com.michaeltchuang.wallet.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import kotlinx.coroutines.launch

private const val SAMPLE_STRING =
    "borrow among leopard smooth trade cake profit proud matrix bottom goose charge oxygen shine punch hotel era monitor fossil violin tip notice any visit"

@Composable
fun AlgoKitBip39Screen(runtimeAwareSdk: RuntimeAwareSdk) {
    val scope = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var entropy by remember { mutableStateOf<String?>(null) }

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Icon Button",
                    modifier =
                        Modifier
                            .size(48.dp)
                            .padding(8.dp),
                )
            }
        }
        Button(onClick = {
            scope.launch {
                if (runtimeAwareSdk.initialize()) {
                    entropy = runtimeAwareSdk.getEntropyFromMnemonic(SAMPLE_STRING)
                    Log.i("AlogKit", "runtimeAwareSdk initialized")
                } else {
                    Log.i("AlogKit", "runtimeAwareSdk not initialize")
                }
            }
        }) {
            Text("Account Create")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            modifier = Modifier.wrapContentSize(),
            text = entropy.toString(),
            fontSize = 18.sp,
        )
    }
}

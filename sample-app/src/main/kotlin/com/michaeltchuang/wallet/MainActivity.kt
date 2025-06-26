package com.michaeltchuang.wallet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import com.michaeltchuang.wallet.ui.AlgoKitBip39Screen
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk

class MainActivity : AppCompatActivity() {
    private val runtimeAwareSdk by lazy { RuntimeAwareSdk(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AlgoKitBip39Screen(runtimeAwareSdk)
            }
        }
    }
}

package com.michaeltchuang.wallet

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.michaeltchuang.wallet.ui.AccountListScreen
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val runtimeAwareSdk by lazy { RuntimeAwareSdk(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgoKitTheme {
                AccountListScreen(runtimeAwareSdk = runtimeAwareSdk)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            if (runtimeAwareSdk.initialize()) {
                Log.i("AlgoKit", "runtimeAwareSdk initialized")
            } else {
                Log.i("AlgoKit", "runtimeAwareSdk not initialize")
            }
        }
    }
}

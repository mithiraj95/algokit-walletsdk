package com.michaeltchuang.wallet

import android.app.Application
import android.util.Log
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppLoader : Application() {
    private val runtimeAwareSdk by lazy { RuntimeAwareSdk(this) }
    override fun onCreate() {
        CoroutineScope(Dispatchers.IO).launch {
            if (runtimeAwareSdk.initialize()) {
                Log.i("AlgoKit", "runtimeAwareSdk initialized")
            } else {
                Log.i("AlgoKit", "runtimeAwareSdk not initialize")
            }
        }
        super.onCreate()
    }
}

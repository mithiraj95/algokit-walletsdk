package com.michaeltchuang.wallet

import android.app.Application
import android.util.Log
import com.michaeltchuang.wallet.di.runtimeAwareSdkModule
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppLoader : Application() {
    override fun onCreate() {

        startKoin {
            androidContext(this@AppLoader)
            modules(runtimeAwareSdkModule)
        }
        CoroutineScope(Dispatchers.IO).launch {
            if (getKoin().get<RuntimeAwareSdk>().initialize()) {
                Log.i("AlgoKit", "runtimeAwareSdk initialized")
            } else {
                Log.i("AlgoKit", "runtimeAwareSdk not initialize")
            }
        }
        super.onCreate()
    }
}

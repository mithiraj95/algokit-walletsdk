package com.michaeltchuang.wallet

import android.app.Application
import com.michaeltchuang.walletsdk.runtimeaware.account.di.accountCoreModule
import com.michaeltchuang.walletsdk.runtimeaware.account.local.di.localAccountsModule
import com.michaeltchuang.walletsdk.runtimeaware.di.viewModelModule
import com.michaeltchuang.walletsdk.runtimeaware.encryption.di.encryptionModule
import com.michaeltchuang.walletsdk.runtimeaware.foundation.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppLoader : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(this@AppLoader)
            modules(
                listOf(
                    commonModule,
                    encryptionModule,
                    localAccountsModule,
                    accountCoreModule,
                    viewModelModule
                )
            )
        }
        super.onCreate()
    }
}
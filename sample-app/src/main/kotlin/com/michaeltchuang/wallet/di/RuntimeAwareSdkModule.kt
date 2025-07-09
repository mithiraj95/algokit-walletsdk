package com.michaeltchuang.wallet.di

import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import org.koin.dsl.module

val runtimeAwareSdkModule = module {
    single { RuntimeAwareSdk(get()) }
}
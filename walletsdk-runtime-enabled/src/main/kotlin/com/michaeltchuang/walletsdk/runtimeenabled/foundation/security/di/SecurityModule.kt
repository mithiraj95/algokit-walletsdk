package com.michaeltchuang.walletsdk.runtimeenabled.foundation.security.di

import com.michaeltchuang.walletsdk.runtimeenabled.foundation.security.AlgoKitSecurityManager
import com.michaeltchuang.walletsdk.runtimeenabled.foundation.security.AlgoKitSecurityManagerImpl
import com.michaeltchuang.walletsdk.runtimeenabled.foundation.security.SecurityManager
import com.michaeltchuang.walletsdk.runtimeenabled.foundation.security.SecurityManagerImpl
import com.michaeltchuang.walletsdk.runtimeenabled.foundation.security.SecurityProvidersFactory
import com.michaeltchuang.walletsdk.runtimeenabled.foundation.security.SecurityProvidersFactoryImpl
import org.koin.dsl.module


val securityModule = module {
    single<SecurityManager> {
        SecurityManagerImpl()
    }
    single<SecurityProvidersFactory> {
        SecurityProvidersFactoryImpl()
    }
    single<AlgoKitSecurityManager> {
        AlgoKitSecurityManagerImpl(get(), get()) // singleton equivalent to @Singleton
    }
}

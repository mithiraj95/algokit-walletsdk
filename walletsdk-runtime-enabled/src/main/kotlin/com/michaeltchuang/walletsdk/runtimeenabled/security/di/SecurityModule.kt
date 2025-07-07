package com.michaeltchuang.walletsdk.runtimeenabled.security.di

import com.michaeltchuang.walletsdk.runtimeenabled.security.AlgoKitSecurityManager
import com.michaeltchuang.walletsdk.runtimeenabled.security.AlgoKitSecurityManagerImpl
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityManager
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityManagerImpl
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityProvidersFactory
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityProvidersFactoryImpl
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

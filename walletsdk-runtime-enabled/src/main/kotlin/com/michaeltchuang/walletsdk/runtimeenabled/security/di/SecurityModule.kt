package com.michaeltchuang.walletsdk.runtimeenabled.security.di

import com.michaeltchuang.walletsdk.runtimeenabled.security.AlgoKitSecurityManager
import com.michaeltchuang.walletsdk.runtimeenabled.security.AlgoKitSecurityManagerImpl
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityManager
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityManagerImpl
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityProvidersFactory
import com.michaeltchuang.walletsdk.runtimeenabled.security.SecurityProvidersFactoryImpl
import org.koin.dsl.module


val securityModule = module {
    single {
        SecurityManagerImpl()
    }
    single<SecurityManager> {
        get<SecurityManagerImpl>()
    }
    single {
        SecurityProvidersFactoryImpl()
    }
    single<SecurityProvidersFactory> {
        get<SecurityProvidersFactoryImpl>()
    }
    single {
        AlgoKitSecurityManagerImpl(get(), get()) // singleton equivalent to @Singleton
    }
    single<AlgoKitSecurityManager> {
        get<AlgoKitSecurityManagerImpl>() // singleton equivalent to @Singleton
    }
}

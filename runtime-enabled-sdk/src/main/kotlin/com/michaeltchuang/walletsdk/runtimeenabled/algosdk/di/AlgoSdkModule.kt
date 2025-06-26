package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.di

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.usecase.RecoverWithPassphrasePreviewUseCase
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoAccountSdkImpl
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39Sdk
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39SdkImpl
import org.koin.dsl.module

val algoSdkModule = module {
    single { AlgoAccountSdkImpl() }
    single<AlgoKitBip39Sdk> { AlgoKitBip39SdkImpl() }
    factory { RecoverWithPassphrasePreviewUseCase(get()) }
}


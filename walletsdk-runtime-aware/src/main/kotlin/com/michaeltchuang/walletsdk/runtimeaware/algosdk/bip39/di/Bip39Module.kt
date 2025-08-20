package com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.di

import com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.sdk.AlgorandBip39WalletProvider
import com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.sdk.Bip39WalletProvider
import org.koin.dsl.module

val bip39Module = module {
    single { AlgorandBip39WalletProvider() }
    single<Bip39WalletProvider> { get<AlgorandBip39WalletProvider>() }
}

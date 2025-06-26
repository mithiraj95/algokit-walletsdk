package com.michaeltchuang.walletsdk.runtimeenabled.runtime.data.service

import android.content.Context
import android.util.Base64
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.di.algoSdkModule
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39SdkImpl
import com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service.WalletSdkService
import org.koin.core.context.startKoin


class WalletSdkServiceImpl(private val context: Context) : WalletSdkService {

    override suspend fun initialize() {
        startKoin {
            modules(algoSdkModule)
        }
    }

    override suspend fun getEntropyFromMnemonic(mnemonic: String): String {
        val entropy = AlgoKitBip39SdkImpl().getEntropyFromMnemonic(mnemonic)
        return Base64.encodeToString(entropy, Base64.NO_WRAP)
    }

    override suspend fun getSeedFromEntropy(entropy: String): String {
        val entropy = Base64.decode(entropy, Base64.NO_WRAP)
        val seed = AlgoKitBip39SdkImpl().getSeedFromEntropy(entropy)
        return Base64.encodeToString(seed, Base64.NO_WRAP)
    }

    override suspend fun getMnemonicFromEntropy(entropy: String): String {
        val entropy = Base64.decode(entropy, Base64.NO_WRAP)
        val mnemonic = AlgoKitBip39SdkImpl().getMnemonicFromEntropy(entropy) ?: ""
        return mnemonic
    }

}

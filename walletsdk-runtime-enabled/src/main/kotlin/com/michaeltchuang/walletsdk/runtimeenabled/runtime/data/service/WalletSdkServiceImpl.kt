package com.michaeltchuang.walletsdk.runtimeenabled.runtime.data.service

import android.content.Context
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.di.algoSdkModule
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model.Algo25Account
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoAccountSdkImpl
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39SdkImpl
import com.michaeltchuang.walletsdk.runtimeenabled.security.di.securityModule
import com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service.WalletSdkService
import org.koin.core.context.startKoin


class WalletSdkServiceImpl(private val context: Context) : WalletSdkService {

    override suspend fun initialize() {
        startKoin {
            modules(securityModule)
            modules(algoSdkModule)
        }
    }

    override suspend fun getEntropyFromMnemonic(mnemonic: String): String {
        return AlgoKitBip39SdkImpl().getEntropyFromMnemonic(mnemonic)?.decodeToString() ?: ""
    }

    override suspend fun getSeedFromEntropy(entropy: String): String {
        val seed = AlgoKitBip39SdkImpl().getSeedFromEntropy(entropy.encodeToByteArray())
        return seed?.decodeToString() ?: ""
    }

    override suspend fun getMnemonicFromEntropy(entropy: String): String {
        val mnemonic =
            AlgoKitBip39SdkImpl().getMnemonicFromEntropy(entropy.encodeToByteArray()) ?: ""
        return mnemonic
    }

    override suspend fun createAlgo25Account(): Algo25Account? {
        val algoAccountSdkImpl = AlgoAccountSdkImpl()
        return algoAccountSdkImpl.createAlgo25Account()
    }

    override suspend fun recoverAlgo25Account(mnemonic: String): Algo25Account? {
        val algoAccountSdkImpl = AlgoAccountSdkImpl()
        return algoAccountSdkImpl.recoverAlgo25Account(mnemonic)
    }

    override suspend fun getMnemonicFromAlgo25SecretKey(secretKey: String): String {
        val algoAccountSdkImpl = AlgoAccountSdkImpl()
        return algoAccountSdkImpl.getMnemonicFromAlgo25SecretKey(secretKey.encodeToByteArray())
            .toString()
    }

}

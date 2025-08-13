package com.michaeltchuang.walletsdk.runtimeenabled.runtime.data.service

import android.content.Context
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.di.bip39Module
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.sdk.AlgorandBip39WalletProvider
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.sdk.Bip39Wallet
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.di.algoSdkModule
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model.Algo25Account
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoAccountSdkImpl
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39Sdk
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39SdkImpl
import com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service.WalletSdkService
import com.michaeltchuang.walletsdk.runtimeenabled.security.AlgoKitSecurityManagerImpl
import com.michaeltchuang.walletsdk.runtimeenabled.security.di.securityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module


class WalletSdkServiceImpl(private val context: Context) : WalletSdkService {

    override suspend fun initialize() {
        initializeKoin()
        initializeSecurityManager()
    }

    override suspend fun getEntropyFromMnemonic(mnemonic: String): String {
        return AlgoKitBip39SdkImpl().getEntropyFromMnemonic(mnemonic) ?: ""
    }

    override suspend fun getSeedFromEntropy(entropy: String): String {
        val seed = AlgoKitBip39SdkImpl().getSeedFromEntropy(entropy)
        return seed ?: ""
    }

    override suspend fun getMnemonicFromEntropy(entropy: String): String {
        val mnemonic =
            AlgoKitBip39SdkImpl().getMnemonicFromEntropy(entropy) ?: ""
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

    override suspend fun createBip39Wallet(): Bip39Wallet {
        val bip39WalletProvider = AlgorandBip39WalletProvider()
        return bip39WalletProvider.createBip39Wallet()
    }

    override suspend fun algoKitBip39Sdk(): AlgoKitBip39Sdk {
        return AlgoKitBip39SdkImpl()
    }

    override suspend fun initializeSecurityManager() {
        return GlobalContext.get().get<AlgoKitSecurityManagerImpl>().initializeSecurityManager()
    }

    private fun initializeKoin() {
        GlobalContext.getOrNull()?.let {
            // Koin already started
            loadKoinModules(
                getModules()
            )
        } ?: run {
            startKoin {
                androidContext(context)
                modules(getModules())
            }
        }
    }

    private fun getModules(): List<Module> {
        return listOf(
            securityModule, algoSdkModule, bip39Module
        )
    }

}

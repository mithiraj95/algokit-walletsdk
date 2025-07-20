package com.michaeltchuang.walletsdk.runtimeaware

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.privacysandbox.sdkruntime.client.SdkSandboxManagerCompat
import androidx.privacysandbox.sdkruntime.core.LoadSdkCompatException
import com.michaeltchuang.walletsdk.runtimeaware.account.di.accountCoreModule
import com.michaeltchuang.walletsdk.runtimeaware.account.di.customInfoModule
import com.michaeltchuang.walletsdk.runtimeaware.account.di.localAccountsModule
import com.michaeltchuang.walletsdk.runtimeaware.account.di.viewModelModule
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.NameRegistrationUseCase
import com.michaeltchuang.walletsdk.runtimeaware.encryption.di.encryptionModule
import com.michaeltchuang.walletsdk.runtimeaware.foundation.commonModule
import com.michaeltchuang.walletsdk.runtimeaware.foundation.delegateModule
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.sdk.Bip39Wallet
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model.Algo25Account
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39Sdk
import com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service.WalletSdkService
import com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service.WalletSdkServiceFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module


class RuntimeAwareSdk(private val context: Context) {

    /**
     * Initialize the SDK. If the SDK failed to initialize, return false, else true.
     */
    suspend fun initialize(): Boolean {
        // You can also have a fallback mechanism here, where if the SDK cannot be loaded in the SDK
        // runtime, initialize as you usually would.
        val isRuntimeEnabledSdkLoaded = loadSdkIfNeeded(context) != null
        initializeKoin()
        return isRuntimeEnabledSdkLoaded
    }

    suspend fun getEntropyFromMnemonic(mnemonic: String): String? {
        return loadSdkIfNeeded(context)?.getEntropyFromMnemonic(mnemonic = mnemonic)
    }

    suspend fun createAlgo25Account(): Algo25Account? {
        return loadSdkIfNeeded(context)?.createAlgo25Account()
    }

    suspend fun fetchAccounts(): List<LocalAccount> {
        return GlobalContext.get().get<NameRegistrationUseCase>().getAccount()
    }

    suspend fun deleteAccount(address: String) {
        GlobalContext.get().get<NameRegistrationUseCase>().deleteAccount(address)
    }
    suspend fun deleteHdKeyAccount(address: String) {
        GlobalContext.get().get<NameRegistrationUseCase>().deleteHdKeyAccount(address)
    }

    suspend fun createBip39Wallet(): Bip39Wallet? {
        return loadSdkIfNeeded(context)?.createBip39Wallet()
    }

    suspend fun algoKitBit39Sdk(): AlgoKitBip39Sdk? {
        return loadSdkIfNeeded(context)?.algoKitBip39Sdk()
    }


    /** Keeps a reference to a sandboxed SDK and makes sure it's only loaded once. */
    companion

    object Loader {

        private const val TAG = "ExistingSdk"

        /**
         * Name of the SDK to be loaded.
         *
         * (needs to be the one defined in runtime-enabled-sdk-bundle/build.gradle.kts)
         */
        private const val SDK_NAME = "com.michaeltchuang.walletsdk.runtimeenabled"

        private var remoteInstance: WalletSdkService? = null

        suspend fun loadSdkIfNeeded(context: Context): WalletSdkService? {
            try {
                // First we need to check if the SDK is already loaded. If it is we just return it.
                // The sandbox manager will throw an exception if we try to load an SDK that is
                // already loaded.
                if (remoteInstance != null) return remoteInstance

                // An [SdkSandboxManagerCompat], used to communicate with the sandbox and load SDKs.
                val sandboxManagerCompat = SdkSandboxManagerCompat.Companion.from(context)

                val sandboxedSdk = sandboxManagerCompat.loadSdk(SDK_NAME, Bundle.EMPTY)
                remoteInstance =
                    WalletSdkServiceFactory.wrapToWalletSdkService(sandboxedSdk.getInterface()!!)
                // Initialize SDK.
                remoteInstance?.initialize()
                return remoteInstance
            } catch (e: LoadSdkCompatException) {
                Log.e(TAG, "Failed to load SDK, error code: ${e.loadSdkErrorCode}", e)
                return null
            }
        }

        fun isSdkLoaded(): Boolean {
            return remoteInstance != null
        }
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
            commonModule,
            encryptionModule,
            localAccountsModule,
            customInfoModule,
            accountCoreModule,
            delegateModule,
            viewModelModule,
        )
    }
}

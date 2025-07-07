package com.michaeltchuang.walletsdk.runtimeaware

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.privacysandbox.sdkruntime.client.SdkSandboxManagerCompat
import androidx.privacysandbox.sdkruntime.core.LoadSdkCompatException
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model.Algo25Account
import com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service.WalletSdkService
import com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service.WalletSdkServiceFactory


/**
 * This class represents an SDK that was created before the SDK runtime was available. It is in the
 * process of migrating to use the SDK runtime. At this point, all of the functionality has been
 * migrated to the "runtime enabled SDK" and this SDK merely serves as a wrapper.
 */

class RuntimeAwareSdk(private val context: Context) {

    /**
     * Initialize the SDK. If the SDK failed to initialize, return false, else true.
     */
    suspend fun initialize(): Boolean {
        // You can also have a fallback mechanism here, where if the SDK cannot be loaded in the SDK
        // runtime, initialize as you usually would.
        val isRuntimeEnabledSdkLoaded = loadSdkIfNeeded(context) != null
        return isRuntimeEnabledSdkLoaded
    }

    suspend fun getEntropyFromMnemonic(mnemonic: String): String? {
        return loadSdkIfNeeded(context)?.getEntropyFromMnemonic(mnemonic = mnemonic)
    }

    suspend fun createAlgo25Account(): Algo25Account? {
        return loadSdkIfNeeded(context)?.createAlgo25Account()
    }

    /** Keeps a reference to a sandboxed SDK and makes sure it's only loaded once. */
    companion object Loader {

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
}

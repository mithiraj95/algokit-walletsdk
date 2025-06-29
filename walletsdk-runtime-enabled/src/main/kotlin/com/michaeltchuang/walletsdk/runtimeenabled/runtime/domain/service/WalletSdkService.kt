package com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service

import androidx.privacysandbox.tools.PrivacySandboxService
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model.Algo25Account

@PrivacySandboxService
interface WalletSdkService {
    suspend fun initialize()
    suspend fun getEntropyFromMnemonic(mnemonic: String): String

    suspend fun getSeedFromEntropy(entropy: String): String

    suspend fun getMnemonicFromEntropy(entropy: String): String

    suspend fun createAlgo25Account(): Algo25Account?

    suspend fun recoverAlgo25Account(mnemonic: String): Algo25Account?

    suspend fun getMnemonicFromAlgo25SecretKey(secretKey: String): String
}

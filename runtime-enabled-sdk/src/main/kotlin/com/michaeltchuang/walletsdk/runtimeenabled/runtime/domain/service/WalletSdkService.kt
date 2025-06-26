package com.michaeltchuang.walletsdk.runtimeenabled.runtime.domain.service

import androidx.privacysandbox.tools.PrivacySandboxService

@PrivacySandboxService
interface WalletSdkService {
    suspend fun initialize()
    suspend fun getEntropyFromMnemonic(mnemonic: String): String

    suspend fun getSeedFromEntropy(entropy: String): String

    suspend fun getMnemonicFromEntropy(entropy: String): String
}

package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk

import androidx.privacysandbox.tools.PrivacySandboxInterface

@PrivacySandboxInterface
interface AlgoKitBip39Sdk {
   suspend fun getSeedFromEntropy(entropy: String): String?
   suspend fun getEntropyFromMnemonic(mnemonic: String): String?
   suspend fun getMnemonicFromEntropy(entropy: String): String?
}

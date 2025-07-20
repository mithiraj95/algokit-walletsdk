package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.usecase

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoKitBip39Sdk

class RecoverWithPassphrasePreviewUseCase(
    private val algokitBip39Sdk: AlgoKitBip39Sdk,
) {
    suspend fun getAccount(
        mnemonics: String,
    ): String? {
        val entropy = algokitBip39Sdk.getEntropyFromMnemonic(mnemonics)
        return entropy
    }
}

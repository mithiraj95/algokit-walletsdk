package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk

import cash.z.ecc.android.bip39.Mnemonics
import cash.z.ecc.android.bip39.toSeed
import com.michaeltchuang.walletsdk.runtimeenabled.utils.base64DecodeToByteArray
import com.michaeltchuang.walletsdk.runtimeenabled.utils.base64EncodeToString

internal class AlgoKitBip39SdkImpl : AlgoKitBip39Sdk {
    override suspend fun getSeedFromEntropy(entropy: String): String? {
        return try {
            Mnemonics.MnemonicCode(entropy.base64DecodeToByteArray()).toSeed().base64EncodeToString()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getEntropyFromMnemonic(mnemonic: String): String? {
        return try {
            Mnemonics.MnemonicCode(mnemonic).toEntropy().base64EncodeToString()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMnemonicFromEntropy(entropy: String): String? {
        return try {
            val mnemonic =
                Mnemonics.MnemonicCode(entropy.base64DecodeToByteArray()).words.joinToString(" ") { charArray ->
                    String(charArray)
                }
            mnemonic
        } catch (e: Exception) {
            null
        }
    }
}

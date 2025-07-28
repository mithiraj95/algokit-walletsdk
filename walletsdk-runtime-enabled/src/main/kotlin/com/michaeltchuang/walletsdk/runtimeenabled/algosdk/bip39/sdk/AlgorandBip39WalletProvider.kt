package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.sdk

import android.util.Log
import cash.z.ecc.android.bip39.Mnemonics
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.Bip39Entropy
import java.util.Base64

class AlgorandBip39WalletProvider : Bip39WalletProvider {

    override fun getBip39Wallet(entropy: ByteArray): Bip39Wallet {
        return AlgorandBip39Wallet(Bip39Entropy(entropy.toString()))
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun createBip39Wallet(): Bip39Wallet {
        val entropy = Mnemonics.MnemonicCode(Mnemonics.WordCount.COUNT_24).toEntropy()
        val base64 = Base64.getEncoder().encodeToString(entropy)
        return AlgorandBip39Wallet(Bip39Entropy(base64))
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.sdk

import cash.z.ecc.android.bip39.Mnemonics
import com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.model.Bip39Entropy

class AlgorandBip39WalletProvider : Bip39WalletProvider {

    override fun getBip39Wallet(entropy: ByteArray): Bip39Wallet {
        return AlgorandBip39Wallet(Bip39Entropy(entropy))
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun createBip39Wallet(): Bip39Wallet {
        val entropy = Mnemonics.MnemonicCode(Mnemonics.WordCount.COUNT_24).toEntropy()
        return AlgorandBip39Wallet(Bip39Entropy(entropy))
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.sdk

interface Bip39WalletProvider {
    fun getBip39Wallet(entropy: ByteArray): Bip39Wallet
    fun createBip39Wallet(): Bip39Wallet
}

package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.sdk

import androidx.privacysandbox.tools.PrivacySandboxInterface
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.Bip39Entropy
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.Bip39Mnemonic
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.Bip39Seed
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddress
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddressIndex
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddressLite

@PrivacySandboxInterface
interface Bip39Wallet {
    suspend fun getEntropy(): Bip39Entropy
    suspend fun getSeed(): Bip39Seed
    suspend fun getMnemonic(): Bip39Mnemonic
    suspend fun generateAddress(index: HdKeyAddressIndex): HdKeyAddress
    suspend fun generateAddressLite(index: HdKeyAddressIndex): HdKeyAddressLite
    suspend fun invalidate()
}

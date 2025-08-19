package com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.model

data class HdKeyAddress(
    val address: String,
    val index: HdKeyAddressIndex,
    val privateKey: String,
    val publicKey: String,
    val derivationType: HdKeyAddressDerivationType
)

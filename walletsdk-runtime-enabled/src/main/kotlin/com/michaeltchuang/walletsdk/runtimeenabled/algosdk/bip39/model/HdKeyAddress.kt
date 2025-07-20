package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model

import androidx.privacysandbox.tools.PrivacySandboxValue

@PrivacySandboxValue
data class HdKeyAddress(
    val address: String,
    val index: HdKeyAddressIndex,
    val privateKey: String,
    val publicKey: String,
    val derivationType: HdKeyAddressDerivationType
)

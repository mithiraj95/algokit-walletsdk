package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model

import androidx.privacysandbox.tools.PrivacySandboxValue

@PrivacySandboxValue
data class HdKeyAddressLite(
    val address: String,
    val index: HdKeyAddressIndex,
)

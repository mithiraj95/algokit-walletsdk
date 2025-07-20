package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model

import androidx.privacysandbox.tools.PrivacySandboxValue

@PrivacySandboxValue
data class HdKeyAddressIndex(
    val accountIndex: Int = 0,
    val changeIndex: Int = 0,
    val keyIndex: Int = 0
)

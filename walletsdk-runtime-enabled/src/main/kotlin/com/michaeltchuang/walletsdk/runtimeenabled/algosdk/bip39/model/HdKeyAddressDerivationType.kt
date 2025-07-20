package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model

import androidx.privacysandbox.tools.PrivacySandboxValue

@PrivacySandboxValue
enum class HdKeyAddressDerivationType(val value: Int) {
    Peikert(9),
    Khovratovich(32)
}

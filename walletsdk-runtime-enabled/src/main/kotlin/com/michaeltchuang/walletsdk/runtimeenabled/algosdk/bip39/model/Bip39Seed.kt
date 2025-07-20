package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model

import androidx.privacysandbox.tools.PrivacySandboxValue

@PrivacySandboxValue
data class Bip39Seed(val value: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bip39Seed

        return value.decodeToByteArray().contentEquals(other.value.decodeToByteArray())
    }

    override fun hashCode(): Int {
        return value.decodeToByteArray().contentHashCode()
    }
}

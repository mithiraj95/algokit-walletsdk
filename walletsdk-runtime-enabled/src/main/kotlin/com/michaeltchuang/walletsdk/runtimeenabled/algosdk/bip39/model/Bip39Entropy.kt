package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model

import androidx.privacysandbox.tools.PrivacySandboxValue
import java.util.Base64

@PrivacySandboxValue
data class Bip39Entropy(val value: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bip39Entropy

        return value.decodeToByteArray().contentEquals(other.value.decodeToByteArray())
    }

    override fun hashCode(): Int {
        return value.decodeToByteArray().contentHashCode()
    }
}
fun String.decodeToByteArray(): ByteArray {
    return Base64.getDecoder().decode(this)
}

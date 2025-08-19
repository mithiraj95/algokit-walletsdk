package com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.model

import java.util.Base64

data class Bip39Entropy(val value: ByteArray) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bip39Entropy

        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }
}
fun String.decodeToByteArray(): ByteArray {
    return Base64.getDecoder().decode(this)
}

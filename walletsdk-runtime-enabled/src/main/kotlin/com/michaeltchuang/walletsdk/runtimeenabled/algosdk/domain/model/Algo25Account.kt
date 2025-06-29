package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model

import androidx.privacysandbox.tools.PrivacySandboxValue

@PrivacySandboxValue
data class Algo25Account(
    val address: String,
    val secretKey: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Algo25Account

        if (address != other.address) return false
        if (!secretKey.encodeToByteArray()
                .contentEquals(other.secretKey.encodeToByteArray())
        ) return false

        return true
    }

    override fun hashCode(): Int {
        var result = address.hashCode()
        result = 31 * result + secretKey.encodeToByteArray().contentHashCode()
        return result
    }
}

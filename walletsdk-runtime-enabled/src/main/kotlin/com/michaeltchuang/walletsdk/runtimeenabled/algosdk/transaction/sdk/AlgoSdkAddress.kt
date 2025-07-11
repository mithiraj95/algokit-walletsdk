package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.AlgorandAddress

interface AlgoSdkAddress {

    fun isValid(address: String): Boolean

    fun generateAddressFromPublicKey(publicKey: ByteArray): AlgorandAddress?

    fun generateAddressFromPublicKey(addressBase64: String): AlgorandAddress?
}

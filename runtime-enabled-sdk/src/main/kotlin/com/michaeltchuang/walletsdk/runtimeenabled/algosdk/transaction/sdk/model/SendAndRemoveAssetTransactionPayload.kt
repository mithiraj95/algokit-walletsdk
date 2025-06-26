package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model

import java.math.BigInteger

data class SendAndRemoveAssetTransactionPayload(
    val senderAddress: String,
    val receiverAddress: String,
    val assetId: Long,
    val amount: BigInteger,
    val noteInByteArray: ByteArray?
)

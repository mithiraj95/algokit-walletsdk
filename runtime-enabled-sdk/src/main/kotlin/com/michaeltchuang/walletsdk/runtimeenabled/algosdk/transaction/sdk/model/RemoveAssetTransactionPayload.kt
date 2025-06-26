package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model

data class RemoveAssetTransactionPayload(
    val senderAddress: String,
    val creatorAddress: String,
    val assetId: Long
)
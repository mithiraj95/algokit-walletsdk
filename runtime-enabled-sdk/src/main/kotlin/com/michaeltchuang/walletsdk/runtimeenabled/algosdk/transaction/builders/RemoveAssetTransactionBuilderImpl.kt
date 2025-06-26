package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.builders

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.Transaction
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoSdk
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model.RemoveAssetTransactionPayload
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model.SuggestedTransactionParams

internal class RemoveAssetTransactionBuilderImpl(
    private val algoSdk: AlgoSdk
) : RemoveAssetTransactionBuilder {

    override fun invoke(
        payload: RemoveAssetTransactionPayload,
        params: SuggestedTransactionParams
    ): Transaction.RemoveAssetTransaction {
        val txnByteArray = createTxnByteArray(payload, params)
        return Transaction.RemoveAssetTransaction(payload.senderAddress, txnByteArray)
    }

    private fun createTxnByteArray(
        payload: RemoveAssetTransactionPayload,
        params: SuggestedTransactionParams
    ): ByteArray {
        return with(payload) {
            algoSdk.createRemoveAssetTxn(
                senderAddress = senderAddress,
                assetId = assetId,
                suggestedTransactionParams = params,
                creatorPublicKey = creatorAddress
            )
        }
    }
}

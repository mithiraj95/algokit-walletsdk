package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.builders

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.Transaction
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoSdk
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model.SendAndRemoveAssetTransactionPayload
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model.SuggestedTransactionParams

internal class SendAndRemoveAssetTransactionBuilderImpl(
    private val algoSdk: AlgoSdk
) : SendAndRemoveAssetTransactionBuilder {

    override fun invoke(
        payload: SendAndRemoveAssetTransactionPayload,
        params: SuggestedTransactionParams
    ): Transaction.SendAndRemoveAssetTransaction {
        val txnByteArray = createTxnByteArray(payload, params)
        return Transaction.SendAndRemoveAssetTransaction(payload.senderAddress, txnByteArray)
    }

    private fun createTxnByteArray(
        payload: SendAndRemoveAssetTransactionPayload,
        params: SuggestedTransactionParams
    ): ByteArray {
        return with(payload) {
            algoSdk.createSendAndRemoveAssetTxn(
                senderAddress = senderAddress,
                receiverAddress = receiverAddress,
                assetId = assetId,
                amount = amount,
                noteInByteArray = noteInByteArray,
                suggestedTransactionParams = params
            )
        }
    }
}

package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.builders

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.Transaction
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoSdk
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model.AlgoTransactionPayload
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model.SuggestedTransactionParams


internal class AlgoTransactionBuilderImpl(
    private val algoSdk: AlgoSdk
) : AlgoTransactionBuilder {

    override fun invoke(
        payload: AlgoTransactionPayload,
        params: SuggestedTransactionParams
    ): Transaction.AlgoTransaction {
        val txnByteArray = createTxnByteArray(payload, params)
        return Transaction.AlgoTransaction(payload.senderAddress, txnByteArray)
    }

    private fun createTxnByteArray(
        payload: AlgoTransactionPayload,
        params: SuggestedTransactionParams
    ): ByteArray {
        return with(payload) {
            algoSdk.createAlgoTransferTxn(
                senderAddress = senderAddress,
                receiverAddress = receiverAddress,
                amount = amount,
                isMax = isMaxAmount,
                noteInByteArray = noteInByteArray,
                suggestedTransactionParams = params
            )
        }
    }
}

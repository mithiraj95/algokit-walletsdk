package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.mapper

import com.algorand.algosdk.sdk.SuggestedParams
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.model.SuggestedTransactionParams

internal interface SuggestedParamsMapper {
    operator fun invoke(params: SuggestedTransactionParams, addGenesis: Boolean): SuggestedParams
}

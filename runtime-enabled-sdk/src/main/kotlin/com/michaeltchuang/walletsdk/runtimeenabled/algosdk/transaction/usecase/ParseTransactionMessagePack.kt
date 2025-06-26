package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.usecase

import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.RawTransaction

fun interface ParseTransactionMessagePack {
    operator fun invoke(txnByteArray: ByteArray): RawTransaction?
}

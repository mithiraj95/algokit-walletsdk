package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

fun interface AddAlgo25Account {
    suspend operator fun invoke(
        address: String,
        secretKey: ByteArray,
        isBackedUp: Boolean,
        customName: String?,
        orderIndex: Int
    )
}

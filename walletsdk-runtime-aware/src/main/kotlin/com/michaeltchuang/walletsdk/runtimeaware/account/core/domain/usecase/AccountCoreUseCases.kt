package com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.usecase

fun interface AddAlgo25Account {
    suspend operator fun invoke(
        address: String,
        secretKey: ByteArray,
        isBackedUp: Boolean,
        customName: String?,
        orderIndex: Int
    )
}

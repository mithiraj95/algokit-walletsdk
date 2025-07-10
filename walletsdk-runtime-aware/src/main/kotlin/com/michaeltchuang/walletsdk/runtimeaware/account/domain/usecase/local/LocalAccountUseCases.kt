package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount


internal fun interface SaveAlgo25Account {
    suspend operator fun invoke(account: LocalAccount.Algo25, privateKey: ByteArray)
}

internal fun interface DeleteAlgo25Account {
    suspend operator fun invoke(address: String)
}

internal fun interface GetAlgo25Account {
    suspend operator fun invoke(): List<LocalAccount.Algo25>
}




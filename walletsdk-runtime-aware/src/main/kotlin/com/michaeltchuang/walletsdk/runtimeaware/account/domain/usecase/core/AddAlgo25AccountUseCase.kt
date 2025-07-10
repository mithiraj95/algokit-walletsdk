package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.SaveAlgo25Account

internal class AddAlgo25AccountUseCase(
    private val saveAlgo25Account: SaveAlgo25Account,
) : AddAlgo25Account {

    override suspend fun invoke(
        address: String,
        secretKey: ByteArray,
        isBackedUp: Boolean,
        customName: String?,
        orderIndex: Int
    ) {
        val account = LocalAccount.Algo25(address)
        saveAlgo25Account(account, secretKey)
    }
}

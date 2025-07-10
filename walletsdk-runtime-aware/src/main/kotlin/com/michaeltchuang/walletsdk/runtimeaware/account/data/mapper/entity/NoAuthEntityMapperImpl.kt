package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.NoAuthEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount


internal class NoAuthEntityMapperImpl : NoAuthEntityMapper {

    override fun invoke(localAccount: LocalAccount.NoAuth): NoAuthEntity {
        return NoAuthEntity(
            algoAddress = localAccount.algoAddress
        )
    }

    override fun invoke(address: String): NoAuthEntity {
        return NoAuthEntity(algoAddress = address)
    }
}

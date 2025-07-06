package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.NoAuthEntity


internal class NoAuthEntityMapperImpl : NoAuthEntityMapper {

    override fun invoke(localAccount: com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth): NoAuthEntity {
        return NoAuthEntity(
            algoAddress = localAccount.algoAddress
        )
    }

    override fun invoke(address: String): NoAuthEntity {
        return NoAuthEntity(algoAddress = address)
    }
}

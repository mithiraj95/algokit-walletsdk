package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.NoAuthEntity


internal class NoAuthMapperImpl : NoAuthMapper {

    override fun invoke(entity: NoAuthEntity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth {
        return com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth(
            algoAddress = entity.algoAddress
        )
    }
}

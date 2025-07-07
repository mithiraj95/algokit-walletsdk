package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.Algo25Entity


internal class Algo25MapperImpl : Algo25Mapper {

    override fun invoke(entity: Algo25Entity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25 {
        return com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25(
            algoAddress = entity.algoAddress
        )
    }
}

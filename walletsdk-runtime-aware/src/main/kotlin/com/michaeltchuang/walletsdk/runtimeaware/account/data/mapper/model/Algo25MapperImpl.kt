package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.Algo25Entity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount


internal class Algo25MapperImpl : Algo25Mapper {

    override fun invoke(entity: Algo25Entity): LocalAccount.Algo25 {
        return LocalAccount.Algo25(
            algoAddress = entity.algoAddress
        )
    }
}

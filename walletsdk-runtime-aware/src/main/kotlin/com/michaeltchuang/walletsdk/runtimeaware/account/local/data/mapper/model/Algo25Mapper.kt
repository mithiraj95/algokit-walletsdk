package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.Algo25Entity

internal interface Algo25Mapper {
    operator fun invoke(entity: Algo25Entity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25
}

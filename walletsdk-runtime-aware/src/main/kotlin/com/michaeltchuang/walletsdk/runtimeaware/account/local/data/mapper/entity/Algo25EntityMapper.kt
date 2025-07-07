package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.Algo25Entity

internal interface Algo25EntityMapper {
    operator fun invoke(
        localAccount: com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25,
        privateKey: ByteArray
    ): Algo25Entity
}

package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.Algo25Entity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

internal interface Algo25EntityMapper {
    operator fun invoke(
        localAccount: LocalAccount.Algo25,
        privateKey: ByteArray
    ): Algo25Entity
}

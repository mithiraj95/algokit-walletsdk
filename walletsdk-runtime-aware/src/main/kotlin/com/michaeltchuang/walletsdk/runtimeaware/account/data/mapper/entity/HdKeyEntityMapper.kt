package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.HdKeyEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

internal interface HdKeyEntityMapper {
    operator fun invoke(
        localAccount: LocalAccount.HdKey,
        privateKey: ByteArray
    ): HdKeyEntity
}

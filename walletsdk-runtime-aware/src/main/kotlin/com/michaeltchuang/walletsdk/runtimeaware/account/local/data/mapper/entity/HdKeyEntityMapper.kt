package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdKeyEntity

internal interface HdKeyEntityMapper {
    operator fun invoke(
        localAccount: com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey,
        privateKey: ByteArray
    ): HdKeyEntity
}

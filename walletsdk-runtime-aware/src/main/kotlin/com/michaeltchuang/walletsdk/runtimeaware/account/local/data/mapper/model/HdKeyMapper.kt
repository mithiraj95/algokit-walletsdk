package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdKeyEntity

internal interface HdKeyMapper {
    operator fun invoke(entity: HdKeyEntity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey
}
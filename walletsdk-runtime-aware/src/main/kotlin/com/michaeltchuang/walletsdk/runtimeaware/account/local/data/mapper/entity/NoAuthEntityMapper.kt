package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.NoAuthEntity

internal interface NoAuthEntityMapper {

    operator fun invoke(localAccount: com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth): NoAuthEntity
    operator fun invoke(address: String): NoAuthEntity
}

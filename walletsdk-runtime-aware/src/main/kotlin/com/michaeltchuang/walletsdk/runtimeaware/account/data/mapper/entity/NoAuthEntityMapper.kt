package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.NoAuthEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

internal interface NoAuthEntityMapper {

    operator fun invoke(localAccount: LocalAccount.NoAuth): NoAuthEntity
    operator fun invoke(address: String): NoAuthEntity
}

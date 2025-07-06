package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.NoAuthEntity

internal interface NoAuthMapper {
    operator fun invoke(entity: NoAuthEntity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth
}

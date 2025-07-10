package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.NoAuthEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

internal interface NoAuthMapper {
    operator fun invoke(entity: NoAuthEntity): LocalAccount.NoAuth
}

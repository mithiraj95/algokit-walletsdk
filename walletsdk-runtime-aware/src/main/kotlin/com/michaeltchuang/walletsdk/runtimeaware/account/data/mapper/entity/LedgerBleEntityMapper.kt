package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.LedgerBleEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

internal interface LedgerBleEntityMapper {

    operator fun invoke(localAccount: LocalAccount.LedgerBle): LedgerBleEntity
}

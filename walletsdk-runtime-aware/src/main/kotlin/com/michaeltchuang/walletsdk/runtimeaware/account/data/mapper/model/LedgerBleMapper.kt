package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.LedgerBleEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

internal interface LedgerBleMapper {
    operator fun invoke(entity: LedgerBleEntity): LocalAccount.LedgerBle
}

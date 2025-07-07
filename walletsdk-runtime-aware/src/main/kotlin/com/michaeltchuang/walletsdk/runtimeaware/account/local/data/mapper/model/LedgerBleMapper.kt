package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.LedgerBleEntity

internal interface LedgerBleMapper {
    operator fun invoke(entity: LedgerBleEntity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle
}

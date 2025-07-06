package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.LedgerBleEntity

internal interface LedgerBleEntityMapper {

    operator fun invoke(localAccount: com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle): LedgerBleEntity
}

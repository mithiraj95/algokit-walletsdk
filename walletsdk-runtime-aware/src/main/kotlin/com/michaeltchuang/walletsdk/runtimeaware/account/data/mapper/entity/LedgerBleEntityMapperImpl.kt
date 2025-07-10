package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.LedgerBleEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

internal class LedgerBleEntityMapperImpl : LedgerBleEntityMapper {

    override fun invoke(localAccount: LocalAccount.LedgerBle): LedgerBleEntity {
        return LedgerBleEntity(
            algoAddress = localAccount.algoAddress,
            deviceMacAddress = localAccount.deviceMacAddress,
            accountIndexInLedger = localAccount.indexInLedger,
            bluetoothName = localAccount.bluetoothName
        )
    }
}

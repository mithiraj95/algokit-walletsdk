package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.LedgerBleEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount


internal class LedgerBleMapperImpl : LedgerBleMapper {
    override fun invoke(entity: LedgerBleEntity): LocalAccount.LedgerBle {
        return LocalAccount.LedgerBle(
            algoAddress = entity.algoAddress,
            deviceMacAddress = entity.deviceMacAddress,
            indexInLedger = entity.accountIndexInLedger,
            bluetoothName = entity.bluetoothName
        )
    }
}

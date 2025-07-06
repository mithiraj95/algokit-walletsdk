package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.LedgerBleEntity


internal class LedgerBleMapperImpl : LedgerBleMapper {
    override fun invoke(entity: LedgerBleEntity): com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle {
        return com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle(
            algoAddress = entity.algoAddress,
            deviceMacAddress = entity.deviceMacAddress,
            indexInLedger = entity.accountIndexInLedger,
            bluetoothName = entity.bluetoothName
        )
    }
}

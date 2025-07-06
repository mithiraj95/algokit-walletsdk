package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.LedgerBleEntity


internal class LedgerBleEntityMapperImpl : LedgerBleEntityMapper {

    override fun invoke(localAccount: com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle): LedgerBleEntity {
        return LedgerBleEntity(
            algoAddress = localAccount.algoAddress,
            deviceMacAddress = localAccount.deviceMacAddress,
            accountIndexInLedger = localAccount.indexInLedger,
            bluetoothName = localAccount.bluetoothName
        )
    }
}

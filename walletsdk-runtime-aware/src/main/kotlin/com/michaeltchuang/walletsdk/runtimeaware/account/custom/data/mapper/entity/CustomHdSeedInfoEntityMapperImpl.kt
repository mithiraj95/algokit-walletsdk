package com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model.CustomHdSeedInfo


internal class CustomHdSeedInfoEntityMapperImpl :
    CustomHdSeedInfoEntityMapper {
    override fun invoke(info: CustomHdSeedInfo): CustomHdSeedInfoEntity {
        return CustomHdSeedInfoEntity(
            seedId = info.seedId,
            entropyCustomName = info.entropyCustomName,
            orderIndex = info.orderIndex,
            isBackedUp = info.isBackedUp
        )
    }
}
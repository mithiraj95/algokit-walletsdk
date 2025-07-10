package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom.CustomHdSeedInfo


internal class CustomHdSeedInfoMapperImpl : CustomHdSeedInfoMapper {
    override fun invoke(entity: CustomHdSeedInfoEntity): CustomHdSeedInfo {
        return CustomHdSeedInfo(
            seedId = entity.seedId,
            entropyCustomName = entity.entropyCustomName,
            orderIndex = entity.orderIndex,
            isBackedUp = entity.isBackedUp
        )
    }
}
package com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model.CustomHdSeedInfo


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
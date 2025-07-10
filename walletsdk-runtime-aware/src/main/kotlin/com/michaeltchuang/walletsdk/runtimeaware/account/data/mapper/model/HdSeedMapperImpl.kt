package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.HdSeedEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.HdSeed

internal class HdSeedMapperImpl : HdSeedMapper {
    override fun invoke(entity: HdSeedEntity): HdSeed {
        return HdSeed(
            seedId = entity.seedId
        )
    }
}

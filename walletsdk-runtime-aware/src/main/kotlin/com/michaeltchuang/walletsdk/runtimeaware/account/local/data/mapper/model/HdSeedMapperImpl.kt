package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdSeedEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.HdSeed


internal class HdSeedMapperImpl : HdSeedMapper {
    override fun invoke(entity: HdSeedEntity): HdSeed {
        return HdSeed(
            seedId = entity.seedId
        )
    }
}

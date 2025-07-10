package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.HdSeedEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.HdSeed

internal interface HdSeedMapper {
    operator fun invoke(entity: HdSeedEntity): HdSeed
}

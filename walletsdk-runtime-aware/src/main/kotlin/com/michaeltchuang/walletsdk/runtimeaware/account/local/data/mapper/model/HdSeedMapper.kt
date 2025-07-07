package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdSeedEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.HdSeed

internal interface HdSeedMapper {
    operator fun invoke(entity: HdSeedEntity): HdSeed
}

package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.HdSeedEntity

internal interface HdSeedEntityMapper {
    operator fun invoke(seedId: Int, entropy: ByteArray, seed: ByteArray): HdSeedEntity
}

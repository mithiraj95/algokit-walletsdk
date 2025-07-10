package com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core

import java.math.BigInteger

data class AssetHolding(
    val amount: BigInteger,
    val assetId: Long,
    val isDeleted: Boolean,
    val isFrozen: Boolean,
    val optedInAtRound: Long?,
    val optedOutAtRound: Long?,
    val status: AssetStatus
)

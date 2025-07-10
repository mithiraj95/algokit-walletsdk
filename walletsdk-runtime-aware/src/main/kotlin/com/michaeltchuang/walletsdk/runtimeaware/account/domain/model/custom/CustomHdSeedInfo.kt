package com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom

data class CustomHdSeedInfo(
    val seedId: Int,
    val entropyCustomName: String,
    val orderIndex: Int,
    val isBackedUp: Boolean
)

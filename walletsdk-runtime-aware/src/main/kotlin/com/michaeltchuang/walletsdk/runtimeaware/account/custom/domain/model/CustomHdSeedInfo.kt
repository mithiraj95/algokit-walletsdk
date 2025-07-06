package com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model

data class CustomHdSeedInfo(
    val seedId: Int,
    val entropyCustomName: String,
    val orderIndex: Int,
    val isBackedUp: Boolean
)

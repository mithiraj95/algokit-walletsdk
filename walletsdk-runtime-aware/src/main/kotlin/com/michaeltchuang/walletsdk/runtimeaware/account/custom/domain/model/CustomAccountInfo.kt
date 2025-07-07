package com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.model

data class CustomAccountInfo(
    val address: String,
    val customName: String?,
    val orderIndex: Int,
    val isBackedUp: Boolean
)

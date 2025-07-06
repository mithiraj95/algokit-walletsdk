package com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model

data class HdWalletSummary(
    val seedId: Int,
    val accountCount: Int,
    val maxAccountIndex: Int,
    val primaryValue: String,
    val secondaryValue: String
)

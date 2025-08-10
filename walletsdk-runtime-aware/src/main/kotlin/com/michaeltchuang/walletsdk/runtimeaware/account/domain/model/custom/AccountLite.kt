package com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.custom

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountRegistrationType

data class AccountLite(
    val address: String,
    val customName: String,
    val registrationType: AccountRegistrationType
)

package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model

data class AccountMnemonic(
    val words: List<String>,
    val type: AccountType
) {

    sealed interface AccountType {
        data object Algo25 : AccountType
        data object HdKey : AccountType
    }
}

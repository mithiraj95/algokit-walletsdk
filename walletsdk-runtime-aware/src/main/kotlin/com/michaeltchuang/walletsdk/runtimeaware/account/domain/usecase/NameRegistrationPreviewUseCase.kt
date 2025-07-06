package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.data.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount


class NameRegistrationPreviewUseCase(
    private val accountAdditionUseCase: AccountAdditionUseCase,
    private val getLocalAccountsUseCase: GetAlgo25AccountUseCase,
    private val deleteAlgo25AccountUseCase: DeleteAlgo25AccountUseCase
) {

    suspend fun addNewAccount(accountCreation: AccountCreation) {
        accountAdditionUseCase.addNewAccount(accountCreation)
    }

    suspend fun getAccount(): List<LocalAccount.Algo25> {
        return getLocalAccountsUseCase()
    }

    suspend fun deleteAccount(address: String) {
        deleteAlgo25AccountUseCase(address)
    }
}

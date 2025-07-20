package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount

class NameRegistrationUseCase(
    private val accountAdditionUseCase: AccountAdditionUseCase,
    /*   private val getLocalAccountsUseCase: GetAlgo25AccountUseCase,
       private val getHdKeyAccountUseCase: GetHdKeyAccountUseCase,*/
    private val getLocalAccountUseCase: GetLocalAccountUseCase,
    private val deleteHdKeyAccountUseCase: DeleteHdKeyAccountUseCase,
    private val deleteAlgo25AccountUseCase: DeleteAlgo25AccountUseCase
) {

    suspend fun addNewAccount(accountCreation: AccountCreation) {
        accountAdditionUseCase.addNewAccount(accountCreation)
    }

    suspend fun getAccount(): List<LocalAccount> {
        return getLocalAccountUseCase()
    }
    suspend fun deleteHdKeyAccount(address: String) {
        deleteHdKeyAccountUseCase(address)
    }

    suspend fun deleteAccount(address: String) {
        deleteAlgo25AccountUseCase(address)
    }
}

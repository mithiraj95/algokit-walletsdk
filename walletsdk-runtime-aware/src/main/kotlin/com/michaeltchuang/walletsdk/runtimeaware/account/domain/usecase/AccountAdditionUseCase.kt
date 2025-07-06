package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.data.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.data.CreateAccount
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager


@Suppress("LongParameterList")
class AccountAdditionUseCase(
    private val addAlgo25Account: AddAlgo25Account,
    private val aesPlatformManager: AESPlatformManager
) {

    suspend fun addNewAccount(accountCreation: AccountCreation) {
        addAccount(accountCreation.toCreateAccount())
    }

    private suspend fun addAccount(createAccount: CreateAccount) {
        when (createAccount.type) {
            is CreateAccount.Type.HdKey -> {}
            is CreateAccount.Type.Algo25 -> createAlgo25Account(createAccount, createAccount.type)
            is CreateAccount.Type.LedgerBle -> {}
            is CreateAccount.Type.NoAuth -> {}
        }
    }

    private suspend fun createAlgo25Account(
        createAccount: CreateAccount,
        type: CreateAccount.Type.Algo25
    ) {
        with(createAccount) {
            //var secretKey = aesPlatformManager.decryptByteArray(type.encryptedSecretKey)
            addAlgo25Account(
                address,
                type.encryptedSecretKey,
                isBackedUp,
                customName,
                createAccount.orderIndex
            )
            // type.clearFromMemory()
        }
    }
}

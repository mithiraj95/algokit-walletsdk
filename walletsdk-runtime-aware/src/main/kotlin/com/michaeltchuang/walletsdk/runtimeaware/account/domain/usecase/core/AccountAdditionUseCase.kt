package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.CreateAccount
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager


@Suppress("LongParameterList")
class AccountAdditionUseCase(
    private val addHdKeyAccount: AddHdKeyAccount,
    private val addHdSeed: AddHdSeed,
    private val addAlgo25Account: AddAlgo25Account,
    private val aesPlatformManager: AESPlatformManager
) {

    suspend fun addNewAccount(accountCreation: AccountCreation) {
        addAccount(accountCreation.toCreateAccount())
    }

    private suspend fun addAccount(createAccount: CreateAccount) {
        when (createAccount.type) {
            is CreateAccount.Type.HdKey -> {
                createHdKeyAccount(createAccount, createAccount.type)
            }

            is CreateAccount.Type.Algo25 -> createAlgo25Account(createAccount, createAccount.type)
            is CreateAccount.Type.LedgerBle -> {}
            is CreateAccount.Type.NoAuth -> {}
        }
    }


    private suspend fun createHdKeyAccount(
        createAccount: CreateAccount,
        type: CreateAccount.Type.HdKey
    ) {
        with(createAccount) {
            aesPlatformManager.decryptByteArray(type.encryptedPrivateKey).let { privateKey ->
                aesPlatformManager.decryptByteArray(type.encryptedEntropy).let { entropy ->
                    val seedIdResult = addHdSeed(entropy)
                    val seedId = seedIdResult.getDataOrNull()
                    if (seedIdResult.isSuccess && seedId != null) {
                        addHdKeyAccount(
                            address,
                            type.publicKey,
                            privateKey,
                            seedId,
                            type.account,
                            type.change,
                            type.keyIndex,
                            type.derivationType,
                            isBackedUp,
                            customName,
                            createAccount.orderIndex
                        )
                    }
                }
            }
        }
    }

    private suspend fun createAlgo25Account(
        createAccount: CreateAccount,
        type: CreateAccount.Type.Algo25
    ) {
        with(createAccount) {
            // var secretKey = aesPlatformManager.decryptByteArray(type.encryptedSecretKey)
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

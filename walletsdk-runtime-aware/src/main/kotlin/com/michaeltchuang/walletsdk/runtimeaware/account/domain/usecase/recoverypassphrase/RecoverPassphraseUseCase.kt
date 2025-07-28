package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.recoverypassphrase

import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.OnboardingAccountType
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.utils.CreationType
import com.michaeltchuang.walletsdk.runtimeaware.utils.toShortenedAddress
import kotlinx.coroutines.flow.flow
import java.util.Locale

@Suppress("LongParameterList")
class RecoverPassphraseUseCase(
    private val runtimeAwareSdk: RuntimeAwareSdk,
    private val aesPlatformManager: AESPlatformManager,
) {

    @SuppressWarnings("LongMethod")
    fun validateEnteredMnemonics(
        mnemonics: String,
        onboardingAccountType: OnboardingAccountType
    ) = flow {
        val accountAddress = ""
        val recoveredAccount = getAccount(onboardingAccountType, mnemonics, accountAddress)
        emit(recoveredAccount)
    }


    private suspend fun getAccount(
        accountType: OnboardingAccountType,
        mnemonics: String,
        accountAddress: String
    ): AccountCreation? {
        return when (accountType) {
            OnboardingAccountType.Algo25 -> {
                val algo25account = runtimeAwareSdk.recoverAlgo25Account(
                    mnemonics.lowercase(Locale.ENGLISH)
                ) ?: return null
                AccountCreation(
                    address = algo25account.address,
                    customName = algo25account.address.toShortenedAddress(),
                    isBackedUp = true,
                    type = AccountCreation.Type.Algo25(
                        aesPlatformManager.encryptByteArray(
                            algo25account.secretKey.toByteArray()
                        )
                    ),
                    creationType = CreationType.RECOVER
                )
            }

            OnboardingAccountType.HdKey -> {
                // only entropy is needed for next screen (importing registered addresses)
                val entropy = runtimeAwareSdk.getEntropyFromMnemonic(mnemonics) ?: return null
                AccountCreation(
                    address = accountAddress,
                    customName = accountAddress.toShortenedAddress(),
                    isBackedUp = true,
                    type = AccountCreation.Type.HdKey(
                        publicKey = ByteArray(0),
                        encryptedPrivateKey = ByteArray(0),
                        encryptedEntropy = aesPlatformManager.encryptByteArray(entropy.toByteArray()),
                        account = 0,
                        change = 0,
                        keyIndex = 0,
                        derivationType = 0
                    ),
                    creationType = CreationType.RECOVER
                )
            }
        }
    }
}

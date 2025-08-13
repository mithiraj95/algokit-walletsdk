package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk

import com.algorand.algosdk.account.Account
import com.algorand.algosdk.mnemonic.Mnemonic
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.domain.model.Algo25Account
import com.michaeltchuang.walletsdk.runtimeenabled.utils.clearFromMemory
import java.security.NoSuchAlgorithmException

internal class AlgoAccountSdkImpl : AlgoAccountSdk {
    override fun createAlgo25Account(): Algo25Account? {
        return try {
            val account = Account()
            val secretKey = Mnemonic.toKey(account.toMnemonic())
            val output = Algo25Account(
                address = account.address.encodeAsString(),
                secretKey = secretKey.decodeToString()
            )
            secretKey.clearFromMemory()
            output
        } catch (e: Exception) {
            null
        }
    }

    override fun getMnemonicFromAlgo25SecretKey(secretKey: ByteArray): String? {
        return try {
            Account(secretKey).toMnemonic()
        } catch (e: NoSuchAlgorithmException) {
            null
        }
    }

    override fun recoverAlgo25Account(mnemonic: String): Algo25Account? {
        return try {
            val account = Account(mnemonic)
            val secretKey = Mnemonic.toKey(account.toMnemonic())
            val output = Algo25Account(
                address = account.address.encodeAsString(),
                secretKey = secretKey.decodeToString()
            )
            secretKey.clearFromMemory()
            output
        } catch (e: Exception) {
            null
        }
    }
}

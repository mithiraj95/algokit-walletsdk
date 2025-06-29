package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk

import android.util.Base64
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.model.AlgorandAddress
import com.michaeltchuang.walletsdk.runtimeenabled.encryption.domain.manager.Base64Manager

internal class AlgoSdkAddressImpl(
    private val base64Manager: Base64Manager
) : AlgoSdkAddress {

    override fun isValid(address: String): Boolean {
        return try {
            //  Sdk.isValidAddress(address)
            return false
        } catch (e: Exception) {
            false
        }
    }

    override fun generateAddressFromPublicKey(publicKey: ByteArray): AlgorandAddress? {
        return try {
            // val address = Sdk.generateAddressFromPublicKey(publicKey)
            AlgorandAddress("address", publicKey)
        } catch (exception: Exception) {
            null
        }
    }

    override fun generateAddressFromPublicKey(addressBase64: String): AlgorandAddress? {
        val publicKey = try {
            base64Manager.decode(addressBase64, Base64.DEFAULT)
        } catch (exception: Exception) {
            return null
        }
        return generateAddressFromPublicKey(publicKey)
    }
}

package com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.sdk

import android.util.Log
import cash.z.ecc.android.bip39.Mnemonics
import cash.z.ecc.android.bip39.toSeed
import com.algorand.algosdk.crypto.Address
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.Bip39Entropy
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.Bip39Mnemonic
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.Bip39Seed
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddress
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddressDerivationType
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddressIndex
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddressLite
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.decodeToByteArray
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.transaction.sdk.AlgoAccountSdkImpl
import com.michaeltchuang.walletsdk.runtimeenabled.utils.clearFromMemory
import foundation.algorand.xhdwalletapi.KeyContext
import foundation.algorand.xhdwalletapi.XHDWalletAPIBase.Companion.getBIP44PathFromContext
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security
import java.util.Base64

internal class AlgorandBip39Wallet internal constructor(private val entropy: Bip39Entropy) :
    Bip39Wallet {

    private val seed: Bip39Seed
    private val mnemonic: Bip39Mnemonic
    //private var walletApi: XHDWalletAPIAndroid?

    init {
        Security.removeProvider("BC")
        Security.insertProviderAt(BouncyCastleProvider(), 0)
        val mnemonicCode = Mnemonics.MnemonicCode(entropy.value.decodeToByteArray())
        val code = Base64.getEncoder().encodeToString(mnemonicCode.toSeed())
        seed = Bip39Seed(code)
        mnemonic = Bip39Mnemonic(mnemonicCode.words.map { it.toString() })
        // walletApi = XHDWalletAPIAndroid(seed.value.decodeToByteArray())
    }

    override suspend fun generateAddress(index: HdKeyAddressIndex): HdKeyAddress {
        val publicKey = generatePublicKey(index)
        return HdKeyAddress(
            address = Address(publicKey).toString(),
            index = index,
            publicKey = publicKey.toString(),
            privateKey = generatePrivateKey(index).toString(),
            derivationType = HdKeyAddressDerivationType.Peikert
        )
    }

    override suspend fun generateAddressLite(index: HdKeyAddressIndex): HdKeyAddressLite {
        val publicKey = generatePublicKey(index)
        return HdKeyAddressLite(
            address = Address(publicKey).toString(),
            index = index
        )
    }

    override suspend fun getEntropy(): Bip39Entropy {
        return Bip39Entropy(entropy.value.encodeToByteArray().copyOf().toString(Charsets.UTF_8))
    }

    override suspend fun getSeed(): Bip39Seed {
        return Bip39Seed(seed.value.toByteArray().copyOf().toString())
    }

    override suspend fun getMnemonic(): Bip39Mnemonic {
        return Bip39Mnemonic(mnemonic.words)
    }

    override suspend fun invalidate() {
        entropy.value.toByteArray().clearFromMemory()
        seed.value.toByteArray().clearFromMemory()
       // walletApi = null
    }

    private fun generatePrivateKey(index: HdKeyAddressIndex): ByteArray {
        /* return walletApi!!.deriveKey(
             fromSeed(seed.value.toByteArray()),
             getBip44Path(index),
             isPrivate = true
         )*/
        return AlgoAccountSdkImpl().createAlgo25Account()?.address!!.toByteArray()
    }

    private fun getBip44Path(index: HdKeyAddressIndex): List<UInt> {
        return getBIP44PathFromContext(
            context = KeyContext.Address,
            account = index.accountIndex.toUInt(),
            change = index.changeIndex.toUInt(),
            keyIndex = index.keyIndex.toUInt()
        )
    }

    private fun generatePublicKey(index: HdKeyAddressIndex): ByteArray {
        /* return walletApi!!.keyGen(
             context = KeyContext.Address,
             account = index.accountIndex.toUInt(),
             change = index.changeIndex.toUInt(),
             keyIndex = index.keyIndex.toUInt(),
             derivationType = Bip32DerivationType.Peikert
         )*/
        val algorandAddress = Address(AlgoAccountSdkImpl().createAlgo25Account()?.address!!)

        // Get the public key bytes
        val publicKeyBytes = algorandAddress.bytes
        return publicKeyBytes
       // return AlgoAccountSdkImpl().createAlgo25Account()?.address!!.toByteArray()
    }
}

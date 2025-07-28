package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeenabled.algosdk.bip39.model.HdKeyAddress


internal class DefaultAccountCreationHdKeyTypeMapperImpl(
    private val aesPlatformManager: AESPlatformManager
) : AccountCreationHdKeyTypeMapper {

    override fun invoke(
        entropy: ByteArray,
        hdKeyAddress: HdKeyAddress,
        seedId: Int?
    ): AccountCreation.Type.HdKey {
        return with(hdKeyAddress) {
            AccountCreation.Type.HdKey(
                publicKey.toByteArray(),
                aesPlatformManager.encryptByteArray(privateKey.toByteArray()),
                aesPlatformManager.encryptByteArray(entropy),
                index.accountIndex,
                index.changeIndex,
                index.keyIndex,
                9,
                seedId
            )
        }
    }
}

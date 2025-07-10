package com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.model.HdKeyEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager


internal class HdKeyEntityMapperImpl(
    private val aesPlatformManager: AESPlatformManager
) : HdKeyEntityMapper {

    override fun invoke(
        localAccount: LocalAccount.HdKey,
        privateKey: ByteArray
    ): HdKeyEntity {
        return HdKeyEntity(
            algoAddress = localAccount.algoAddress,
            publicKey = localAccount.publicKey,
            encryptedPrivateKey = aesPlatformManager.encryptByteArray(privateKey),
            seedId = localAccount.seedId,
            account = localAccount.account,
            change = localAccount.change,
            keyIndex = localAccount.keyIndex,
            derivationType = localAccount.derivationType
        )
    }
}

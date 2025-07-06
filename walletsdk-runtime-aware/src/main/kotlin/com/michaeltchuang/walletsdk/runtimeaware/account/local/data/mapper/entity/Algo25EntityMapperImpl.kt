package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.Algo25Entity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager


internal class Algo25EntityMapperImpl(
    private val aesPlatformManager: AESPlatformManager
) : Algo25EntityMapper {

    override fun invoke(localAccount: LocalAccount.Algo25, privateKey: ByteArray): Algo25Entity {
        return Algo25Entity(
            algoAddress = localAccount.algoAddress,
            encryptedSecretKey = aesPlatformManager.encryptByteArray(privateKey),
        )
    }
}

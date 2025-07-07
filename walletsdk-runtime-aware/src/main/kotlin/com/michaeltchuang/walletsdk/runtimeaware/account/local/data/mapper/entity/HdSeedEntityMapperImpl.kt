package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdSeedEntity
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager

internal class HdSeedEntityMapperImpl(
    private val aesPlatformManager: AESPlatformManager
) : HdSeedEntityMapper {

    override fun invoke(seedId: Int, entropy: ByteArray, seed: ByteArray): HdSeedEntity {
        return HdSeedEntity(
            seedId = 0, // Let Room auto-generate the ID
            encryptedEntropy = aesPlatformManager.encryptByteArray(entropy),
            encryptedSeed = aesPlatformManager.encryptByteArray(seed)
        )
    }
}

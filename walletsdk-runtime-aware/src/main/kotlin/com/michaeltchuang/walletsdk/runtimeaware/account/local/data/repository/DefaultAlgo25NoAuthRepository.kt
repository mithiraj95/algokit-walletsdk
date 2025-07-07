package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.repository

import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.Algo25NoAuthDao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.Algo25Entity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity.NoAuthEntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager


internal class DefaultAlgo25NoAuthRepository(
    private val algo25NoAuthDao: Algo25NoAuthDao,
    private val noAuthEntityMapper: NoAuthEntityMapper,
    private val aesPlatformManager: AESPlatformManager,
) : com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository.Algo25NoAuthRepository {

    override suspend fun updateInvalidAlgo25AccountsToNoAuth() {
        val algo25Entities = algo25NoAuthDao.getAllAlgo25Entities()
        val invalidAlgo25Addresses = algo25Entities.mapNotNull { entity ->
            entity.algoAddress.takeIf { entity.isSecretKeyInvalid() }
        }
        val entities = invalidAlgo25Addresses.map { noAuthEntityMapper(it) }
        algo25NoAuthDao.updateAlgo25AccountsToNoAuthAccounts(entities)
    }

    private fun Algo25Entity.isSecretKeyInvalid(): Boolean {
        if (encryptedSecretKey.isEmpty() || encryptedSecretKey.contentEquals(byteArrayOf(0))) return true
        val decryptedSecretKey = aesPlatformManager.decryptByteArray(encryptedSecretKey)
        return decryptedSecretKey.isEmpty() || decryptedSecretKey.contentEquals(byteArrayOf(0))
    }
}

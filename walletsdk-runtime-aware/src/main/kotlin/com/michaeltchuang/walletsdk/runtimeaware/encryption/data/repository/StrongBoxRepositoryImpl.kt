package com.michaeltchuang.walletsdk.runtimeaware.encryption.data.repository

import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.repository.StrongBoxRepository
import com.michaeltchuang.walletsdk.runtimeaware.foundation.cache.PersistentCache


class StrongBoxRepositoryImpl(
    private val strongBoxUsedStorage: PersistentCache<Boolean>,
) : StrongBoxRepository {

    override suspend fun saveStrongBoxUsed(check: Boolean) {
        strongBoxUsedStorage.put(check)
    }

    override suspend fun getStrongBoxUsed(): Boolean {
        return strongBoxUsedStorage.get() ?: false
    }
}

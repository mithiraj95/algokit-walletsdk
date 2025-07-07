package com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.usecase

import com.michaeltchuang.walletsdk.runtimeaware.foundation.AlgoKitResult
import javax.crypto.SecretKey

interface AndroidEncryptionManager {
    fun getSecretKey(): SecretKey
    suspend fun initializeEncryptionManager()
    suspend fun shouldMigrateToStrongBox(): Boolean
    suspend fun migrateToStrongBox(): AlgoKitResult<Boolean>
}

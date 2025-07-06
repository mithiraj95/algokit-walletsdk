package com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.repository

internal interface StrongBoxRepository {
    suspend fun saveStrongBoxUsed(check: Boolean)
    suspend fun getStrongBoxUsed(): Boolean
}

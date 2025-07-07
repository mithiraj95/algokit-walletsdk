package com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository

import kotlinx.coroutines.flow.Flow

interface Algo25AccountRepository {

    fun getAllAsFlow(): Flow<List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25>>

    fun getAccountCountAsFlow(): Flow<Int>

    suspend fun getAccountCount(): Int

    suspend fun getAll(): List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25>

    suspend fun getAllAddresses(): List<String>

    suspend fun getAccount(address: String): _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25?

    suspend fun addAccount(
        account: _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.Algo25,
        privateKey: ByteArray
    )

    suspend fun deleteAccount(address: String)

    suspend fun deleteAllAccounts()

    suspend fun getSecretKey(address: String): ByteArray?
}

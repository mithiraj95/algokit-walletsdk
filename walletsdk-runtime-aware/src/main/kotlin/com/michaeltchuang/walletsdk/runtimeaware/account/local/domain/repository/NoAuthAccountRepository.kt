package com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface NoAuthAccountRepository {

    fun getAllAsFlow(): Flow<List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth>>

    fun getAccountCountAsFlow(): Flow<Int>

    suspend fun getAccountCount(): Int

    suspend fun getAll(): List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth>

    suspend fun getAllAddresses(): List<String>

    suspend fun getAccount(address: String): _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth?

    suspend fun addAccount(account: _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.NoAuth)

    suspend fun deleteAccount(address: String)

    suspend fun isAddressExists(address: String): Boolean

    suspend fun deleteAllAccounts()
}

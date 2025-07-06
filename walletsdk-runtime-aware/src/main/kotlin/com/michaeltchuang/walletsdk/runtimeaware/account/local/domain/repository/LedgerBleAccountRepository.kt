package com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository

import kotlinx.coroutines.flow.Flow

internal interface LedgerBleAccountRepository {

    fun getAllAsFlow(): Flow<List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle>>

    fun getAccountCountAsFlow(): Flow<Int>

    suspend fun getAccountCount(): Int

    suspend fun getAll(): List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle>

    suspend fun getAllAddresses(): List<String>

    suspend fun getAccount(address: String): _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle?

    suspend fun addAccount(account: _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.LedgerBle)

    suspend fun deleteAccount(address: String)

    suspend fun deleteAllAccounts()
}

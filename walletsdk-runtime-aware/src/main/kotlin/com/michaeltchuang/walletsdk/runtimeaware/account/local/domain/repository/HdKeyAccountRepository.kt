package com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository

import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.HdWalletSummary
import kotlinx.coroutines.flow.Flow

internal interface HdKeyAccountRepository {

    fun getAllAsFlow(): Flow<List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey>>

    fun getAccountCountAsFlow(): Flow<Int>

    suspend fun getAccountCount(): Int

    suspend fun getAll(): List<_root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey>

    suspend fun getAllAddresses(): List<String>

    suspend fun getAccount(address: String): _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey?

    suspend fun getDerivedAddressCountOfSeed(seedId: Int): Int

    suspend fun addAccount(
        account: _root_ide_package_.com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount.HdKey,
        privateKey: ByteArray
    )

    suspend fun deleteAccount(address: String)

    suspend fun deleteAllAccounts()

    suspend fun getPrivateKey(address: String): ByteArray?

    suspend fun getHdWalletSummaries(): List<HdWalletSummary>

    suspend fun getHdSeedId(address: String): Int?
}

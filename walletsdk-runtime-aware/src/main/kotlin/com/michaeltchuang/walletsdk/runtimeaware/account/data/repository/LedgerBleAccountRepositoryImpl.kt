package com.michaeltchuang.walletsdk.runtimeaware.account.data.repository

import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.dao.LedgerBleDao
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.LedgerBleEntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.LedgerBleMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount.LedgerBle
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.LedgerBleAccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class LedgerBleAccountRepositoryImpl(
    private val ledgerBleDao: LedgerBleDao,
    private val ledgerBleEntityMapper: LedgerBleEntityMapper,
    private val ledgerBleMapper: LedgerBleMapper,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LedgerBleAccountRepository {

    override fun getAllAsFlow(): Flow<List<LedgerBle>> {
        return ledgerBleDao.getAllAsFlow().map { entityList ->
            entityList.map { entity -> ledgerBleMapper(entity) }
        }
    }

    override fun getAccountCountAsFlow(): Flow<Int> {
        return ledgerBleDao.getTableSizeAsFlow()
    }

    override suspend fun getAccountCount(): Int {
        return ledgerBleDao.getTableSize()
    }

    override suspend fun getAll(): List<LedgerBle> {
        return withContext(coroutineDispatcher) {
            val ledgerBleEntities = ledgerBleDao.getAll()
            ledgerBleEntities.map { ledgerBleMapper(it) }
        }
    }

    override suspend fun getAllAddresses(): List<String> {
        return withContext(coroutineDispatcher) {
            ledgerBleDao.getAllAddresses()
        }
    }

    override suspend fun getAccount(address: String): LedgerBle? {
        return withContext(coroutineDispatcher) {
            val ledgerBleEntity = ledgerBleDao.get(address)
            ledgerBleEntity?.let { ledgerBleMapper(it) }
        }
    }

    override suspend fun addAccount(account: LedgerBle) {
        withContext(coroutineDispatcher) {
            val ledgerBleEntity = ledgerBleEntityMapper(account)
            ledgerBleDao.insert(ledgerBleEntity)
        }
    }

    override suspend fun deleteAccount(address: String) {
        withContext(coroutineDispatcher) {
            ledgerBleDao.delete(address)
        }
    }

    override suspend fun deleteAllAccounts() {
        withContext(coroutineDispatcher) {
            ledgerBleDao.clearAll()
        }
    }
}

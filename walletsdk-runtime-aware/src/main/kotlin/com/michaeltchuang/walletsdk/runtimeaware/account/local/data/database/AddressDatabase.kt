package com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.AddressDatabase.Companion.DATABASE_VERSION
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.Algo25Dao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.Algo25NoAuthDao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.HdKeyDao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.HdSeedDao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.LedgerBleDao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.NoAuthDao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.Algo25Entity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdKeyEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.HdSeedEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.LedgerBleEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.model.NoAuthEntity

@Database(
    entities = [
        LedgerBleEntity::class,
        NoAuthEntity::class,
        HdKeyEntity::class,
        HdSeedEntity::class,
        Algo25Entity::class
    ],
    version = DATABASE_VERSION
)
internal abstract class AddressDatabase : RoomDatabase() {

    abstract fun ledgerBleDao(): LedgerBleDao
    abstract fun noAuthDao(): NoAuthDao
    abstract fun hdKeyDao(): HdKeyDao
    abstract fun hdSeedDao(): HdSeedDao
    abstract fun algo25Dao(): Algo25Dao
    abstract fun algo25NoAuthDao(): Algo25NoAuthDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "address_database"
    }
}

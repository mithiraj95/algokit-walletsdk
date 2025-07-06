package com.michaeltchuang.walletsdk.runtimeaware.foundation.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.dao.CustomAccountInfoDao
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.dao.CustomHdSeedInfoDao
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomAccountInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.database.model.CustomHdSeedInfoEntity
import com.michaeltchuang.walletsdk.runtimeaware.foundation.database.converters.BigDecimalTypeConverter
import com.michaeltchuang.walletsdk.runtimeaware.foundation.database.converters.BigIntegerTypeConverter

@TypeConverters(
    BigIntegerTypeConverter::class,
    BigDecimalTypeConverter::class,
)
@Database(
    entities = [
        CustomAccountInfoEntity::class,
        CustomHdSeedInfoEntity::class
    ],
    version = PeraDatabase.DATABASE_VERSION
)
internal abstract class PeraDatabase : RoomDatabase() {
    abstract fun customAccountInfoDao(): CustomAccountInfoDao
    abstract fun customHdSeedInfoDao(): CustomHdSeedInfoDao

    companion object {
        const val DATABASE_VERSION = 4
        const val DATABASE_NAME = "pera_database"
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.foundation.database

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

val algoKitDatabaseModule = module {

    single {
        Room.databaseBuilder(
            context = get<Context>(),
            klass = AlgoKitDatabase::class.java,
            name = AlgoKitDatabase.DATABASE_NAME
        )
            .build()
    }
}


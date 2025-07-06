package com.michaeltchuang.walletsdk.runtimeaware.foundation.database

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

val peraDatabaseModule = module {

    single {
        Room.databaseBuilder(
            context = get<Context>(),
            klass = PeraDatabase::class.java,
            name = PeraDatabase.DATABASE_NAME
        )
            .build()
    }
}


package com.michaeltchuang.walletsdk.runtimeaware.account.di

import androidx.room.Room
import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.dao.Algo25Dao
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.Algo25EntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.Algo25EntityMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.Algo25Mapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.Algo25MapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.repository.Algo25AccountRepositoryImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.Algo25AccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.SaveAlgo25Account
import com.michaeltchuang.walletsdk.runtimeaware.foundation.database.AlgoKitDatabase
import kotlin.jvm.java
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localAccountsModule = module {
    single<AlgoKitDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AlgoKitDatabase::class.java,
            name = AlgoKitDatabase.DATABASE_NAME
        ).build()
    }

    single<Algo25Dao> { get<AlgoKitDatabase>().algo25Dao() }
    single<Algo25EntityMapper> { Algo25EntityMapperImpl(get()) }
    single<Algo25Mapper> { Algo25MapperImpl() }

    single<Algo25AccountRepository> {
        Algo25AccountRepositoryImpl(get(), get(), get(), get(), get())
    }

    factory { SaveAlgo25Account(get<Algo25AccountRepository>()::addAccount) }
}



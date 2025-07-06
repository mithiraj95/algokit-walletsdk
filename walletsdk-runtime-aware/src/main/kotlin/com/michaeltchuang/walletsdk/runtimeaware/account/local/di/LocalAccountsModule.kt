package com.michaeltchuang.walletsdk.runtimeaware.account.local.di

import androidx.room.Room
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.AddressDatabase
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.database.dao.Algo25Dao
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity.Algo25EntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.entity.Algo25EntityMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model.Algo25Mapper
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.mapper.model.Algo25MapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.local.data.repository.Algo25AccountRepositoryImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.repository.Algo25AccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.usecase.SaveAlgo25Account
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localAccountsModule = module {

    single<AddressDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AddressDatabase::class.java,
            name = AddressDatabase.DATABASE_NAME
        ).build()
    }

    single<Algo25Dao> { get<AddressDatabase>().algo25Dao() }

    //mapper
    single { Algo25EntityMapperImpl(get()) }
    factory<Algo25EntityMapper> { get<Algo25EntityMapperImpl>() }
    single { Algo25MapperImpl() }
    factory<Algo25Mapper> { get<Algo25MapperImpl>() }


    // Repositories
    single { Algo25AccountRepositoryImpl(get(), get(), get(), get(), get()) }
    factory<Algo25AccountRepository> { get<Algo25AccountRepositoryImpl>() }

    // UseCases
    factory { SaveAlgo25Account(get<Algo25AccountRepository>()::addAccount) }
}



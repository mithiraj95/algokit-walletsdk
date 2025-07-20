package com.michaeltchuang.walletsdk.runtimeaware.account.di

import androidx.room.Room
import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.dao.Algo25Dao
import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.dao.HdKeyDao
import com.michaeltchuang.walletsdk.runtimeaware.account.data.database.dao.HdSeedDao
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.Algo25EntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.Algo25EntityMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.HdKeyEntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.HdKeyEntityMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.HdSeedEntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.HdSeedEntityMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.Algo25Mapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.Algo25MapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.HdKeyMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.HdKeyMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.HdSeedMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.HdSeedMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.HdWalletSummaryMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.model.HdWalletSummaryMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.repository.Algo25AccountRepositoryImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.repository.HdKeyAccountRepositoryImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.data.repository.HdSeedRepositoryImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.Algo25AccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.HdKeyAccountRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.HdSeedRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.AddHdKeyAccount
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.AddHdKeyAccountUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.AddHdSeed
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.AddHdSeedUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.GetSeedIdIfExistingEntropy
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.SaveAlgo25Account
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.SaveHdKeyAccount
import com.michaeltchuang.walletsdk.runtimeaware.foundation.database.AlgoKitDatabase
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


    single<HdKeyDao> { get<AlgoKitDatabase>().hdKeyDao() }
    single<HdKeyEntityMapper> { HdKeyEntityMapperImpl(get()) }
    single<HdWalletSummaryMapper> { HdWalletSummaryMapperImpl() }
    single<HdKeyMapper> { HdKeyMapperImpl() }

    single<HdKeyAccountRepository> {
        HdKeyAccountRepositoryImpl(get(), get(), get(), get(), get())
    }

    factory { SaveHdKeyAccount(get<HdKeyAccountRepository>()::addAccount) }

    single<AddHdKeyAccount> { AddHdKeyAccountUseCase(get(), get()) }


    single<HdSeedDao> { get<AlgoKitDatabase>().hdSeedDao() }
    single<HdSeedEntityMapper> { HdSeedEntityMapperImpl(get()) }
    single<HdSeedMapper> { HdSeedMapperImpl() }
    single<HdSeedRepository> { HdSeedRepositoryImpl(get(), get(), get(), get(), get()) }
    factory { GetSeedIdIfExistingEntropy(get<HdSeedRepository>()::getSeedIdIfExistingEntropy) }
    single<AddHdSeed> { AddHdSeedUseCase(get(),get(),get(),get()) }
}



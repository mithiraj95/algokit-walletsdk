package com.michaeltchuang.walletsdk.runtimeaware.account.custom.di

import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.entity.CustomAccountInfoEntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.entity.CustomAccountInfoEntityMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.entity.CustomHdSeedInfoEntityMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.entity.CustomHdSeedInfoEntityMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model.CustomAccountInfoMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model.CustomAccountInfoMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model.CustomHdSeedInfoMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.mapper.model.CustomHdSeedInfoMapperImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.repository.CustomAccountInfoRepositoryImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.data.repository.CustomHdSeedInfoRepositoryImpl
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.repository.CustomAccountInfoRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.repository.CustomHdSeedInfoRepository
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.ClearAllCustomInformationUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.DeleteAccountCustomInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.DeleteHdSeedCustomInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAccountBackUpStatus
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAccountCustomInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAccountCustomInfoOrNull
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAccountCustomName
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAccountsCustomInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAccountsCustomInfoFlow
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAllAccountOrderIndexes
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetAllHdSeedOrderIndexes
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetBackedUpAccounts
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetHdSeedAsbBackUpStatus
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetHdSeedCustomInfoOrNull
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetHdSeedCustomName
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.GetNotBackedUpAccounts
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.SetAccountCustomInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.SetAccountCustomName
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.SetAccountOrderIndex
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.SetAddressesBackedUp
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.SetHdSeedCustomInfo
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.SetHdSeedCustomName
import com.michaeltchuang.walletsdk.runtimeaware.account.custom.domain.usecase.SetHdSeedOrderIndex
import com.michaeltchuang.walletsdk.runtimeaware.foundation.database.PeraDatabase

import org.koin.dsl.module


val customInfoModule = module {

    // DAOs
    single { get<PeraDatabase>().customAccountInfoDao() }
    single { get<PeraDatabase>().customHdSeedInfoDao() }

    // Mappers
    factory<CustomAccountInfoEntityMapper> { CustomAccountInfoEntityMapperImpl() }
    factory<CustomAccountInfoMapper> { CustomAccountInfoMapperImpl() }
    factory<CustomHdSeedInfoEntityMapper> { CustomHdSeedInfoEntityMapperImpl() }
    factory<CustomHdSeedInfoMapper> { CustomHdSeedInfoMapperImpl() }

    // Repositories
    single<CustomAccountInfoRepository> { CustomAccountInfoRepositoryImpl(get(), get(), get()) }
    single<CustomHdSeedInfoRepository> { CustomHdSeedInfoRepositoryImpl(get(), get(), get()) }

    // Use Cases - Account
    factory { SetAccountCustomInfo(get<CustomAccountInfoRepository>()::setCustomInfo) }
    factory { SetAccountCustomName(get<CustomAccountInfoRepository>()::setCustomName) }
    factory { GetAccountCustomName(get<CustomAccountInfoRepository>()::getCustomName) }
    factory { GetAccountsCustomInfoFlow(get<CustomAccountInfoRepository>()::getCustomInfoFlow) }
    factory { GetAccountsCustomInfo(get<CustomAccountInfoRepository>()::getCustomInfos) }
    factory { GetAccountCustomInfoOrNull(get<CustomAccountInfoRepository>()::getCustomInfoOrNull) }
    factory { GetAccountCustomInfo(get<CustomAccountInfoRepository>()::getCustomInfo) }
    factory { SetAccountOrderIndex(get<CustomAccountInfoRepository>()::setOrderIndex) }
    factory { DeleteAccountCustomInfo(get<CustomAccountInfoRepository>()::deleteCustomInfo) }
    factory { GetAllAccountOrderIndexes(get<CustomAccountInfoRepository>()::getAllAccountOrderIndexes) }
    factory { GetNotBackedUpAccounts(get<CustomAccountInfoRepository>()::getNotBackedUpAccounts) }
    factory { GetAccountBackUpStatus(get<CustomAccountInfoRepository>()::isAccountBackedUp) }
    factory { GetBackedUpAccounts(get<CustomAccountInfoRepository>()::getBackedUpAccounts) }
    factory { SetAddressesBackedUp(get<CustomAccountInfoRepository>()::setAddressesBackedUp) }

    // Use Cases - HD Seed
    factory { SetHdSeedCustomInfo(get<CustomHdSeedInfoRepository>()::setCustomInfo) }
    factory { SetHdSeedCustomName(get<CustomHdSeedInfoRepository>()::setCustomName) }
    factory { GetHdSeedCustomName(get<CustomHdSeedInfoRepository>()::getCustomName) }
    factory { GetHdSeedCustomInfoOrNull(get<CustomHdSeedInfoRepository>()::getCustomInfoOrNull) }
    factory { SetHdSeedOrderIndex(get<CustomHdSeedInfoRepository>()::setOrderIndex) }
    factory { DeleteHdSeedCustomInfo(get<CustomHdSeedInfoRepository>()::deleteCustomInfo) }
    factory { GetAllHdSeedOrderIndexes(get<CustomHdSeedInfoRepository>()::getAllHdSeedOrderIndexes) }
    factory { GetHdSeedAsbBackUpStatus(get<CustomHdSeedInfoRepository>()::isHdSeedBackedUp) }

    // Clear all
    factory { ClearAllCustomInformationUseCase(get(), get()) }
}



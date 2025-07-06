package com.michaeltchuang.walletsdk.runtimeaware.account.di

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.AccountAdditionUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.AddAlgo25Account
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.AddAlgo25AccountUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.DeleteAlgo25AccountUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.GetAlgo25AccountUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.NameRegistrationPreviewUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.usecase.DeleteAlgo25Account
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.usecase.GetAlgo25Account
import org.koin.dsl.module

val accountCoreModule = module {
    single { AddAlgo25AccountUseCase(get()) }

    factory<AddAlgo25Account> { get<AddAlgo25AccountUseCase>() }
    single { AccountAdditionUseCase(get(), get()) }


    single { GetAlgo25AccountUseCase(get(), get()) }
    single<GetAlgo25Account> { get<GetAlgo25AccountUseCase>() }

    single { DeleteAlgo25AccountUseCase(get()) }
    single<DeleteAlgo25Account> { get<DeleteAlgo25AccountUseCase>() }

    single { NameRegistrationPreviewUseCase(get(), get(), get()) }
}
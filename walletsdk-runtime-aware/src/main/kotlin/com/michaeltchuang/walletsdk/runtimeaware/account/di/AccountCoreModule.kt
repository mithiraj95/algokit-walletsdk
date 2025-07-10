package com.michaeltchuang.walletsdk.runtimeaware.account.di

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.AccountAdditionUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.AddAlgo25Account
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.AddAlgo25AccountUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.DeleteAlgo25AccountUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.GetAlgo25AccountUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.NameRegistrationPreviewUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.DeleteAlgo25Account
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.GetAlgo25Account
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

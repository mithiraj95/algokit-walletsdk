package com.michaeltchuang.walletsdk.runtimeaware.di

import com.michaeltchuang.walletsdk.runtimeaware.viewmodel.NameRegistrationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        NameRegistrationViewModel(get(), get())
    }
}



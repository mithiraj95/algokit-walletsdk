package com.michaeltchuang.walletsdk.runtimeaware.ui.di

import com.michaeltchuang.walletsdk.runtimeaware.ui.viewmodel.CreateAccountNameViewModel
import com.michaeltchuang.walletsdk.runtimeaware.ui.viewmodel.CreateAccountTypeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        CreateAccountTypeViewModel(
            get(),
            get(),
        )
    }

    viewModel {
        CreateAccountNameViewModel(
            get(),
            get(),
            get(),
        )
    }
}




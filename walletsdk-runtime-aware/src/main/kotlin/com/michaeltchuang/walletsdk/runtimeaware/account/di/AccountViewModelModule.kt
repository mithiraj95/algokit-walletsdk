package com.michaeltchuang.walletsdk.runtimeaware.account.di


import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.CreateAccountNameViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.CreateAccountTypeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        CreateAccountTypeViewModel(
            get(),
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




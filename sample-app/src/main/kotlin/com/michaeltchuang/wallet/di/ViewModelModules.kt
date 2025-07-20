package com.michaeltchuang.wallet.di

import com.michaeltchuang.wallet.ui.viewmodel.AccountListViewModel
import com.michaeltchuang.wallet.ui.widgets.snackbar.SnackbarViewModel
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateDelegate
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val provideViewModelModules =
    module {
        single { SnackbarViewModel() }
        single { StateDelegate<Any>() }
        single { EventDelegate<Any>() }
        viewModel<AccountListViewModel> {
            AccountListViewModel(
                get(),
                get(),
                get(),
            )
        }
    }

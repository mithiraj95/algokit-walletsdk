package com.michaeltchuang.walletsdk.runtimeaware.account.di


import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.CreateAccountNameViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.CreateAccountTypeViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.HDWalletSelectionViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.QRScannerViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.RecoverPassphraseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        CreateAccountTypeViewModel(
            get(),
            get(),
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
            get(),
        )
    }

    viewModel {
        HDWalletSelectionViewModel(
            get(),
            get(),
            get(),
            get(),
        )
    }

    viewModel {
        QRScannerViewModel(get(), get())
    }

    viewModel {
        RecoverPassphraseViewModel(get(), get(),get())
    }
}




package com.michaeltchuang.wallet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AccountListViewModel(
    private val runtimeAwareSdk: RuntimeAwareSdk,
    private val stateDelegate: StateDelegate<AccountsState>,
) : ViewModel(), StateViewModel<AccountListViewModel.AccountsState> by stateDelegate {

    init {
        stateDelegate.setDefaultState(AccountsState(emptyList()))
        viewModelScope.launch {
            fetchAccount()
        }
    }

   suspend fun processIntent(intent: AccountsIntent) {
        when (intent) {
            AccountsIntent.FetchAccount -> {
                fetchAccount()
            }

            is AccountsIntent.DeleteAccount -> {
                deleteAccount(intent.address)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
   suspend fun fetchAccount() {
        viewModelScope.async {
            runtimeAwareSdk.fetchAccounts()
        }.await().let { it1->
            stateDelegate.updateState {
                AccountsState(it1)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun deleteAccount(address: String) {
        viewModelScope.async {
            runtimeAwareSdk.deleteAccount(address)
        }.await().let {
            fetchAccount()
        }

    }

    sealed interface AccountsIntent {
        data object FetchAccount : AccountsIntent
        data class DeleteAccount(val address: String) : AccountsIntent
    }

    data class AccountsState(val accounts: List<LocalAccount.Algo25>)

}

package com.michaeltchuang.wallet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.local.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateViewModel
import kotlinx.coroutines.launch

class AccountListViewModel(
    private val runtimeAwareSdk: RuntimeAwareSdk,
    private val stateDelegate: StateDelegate<AccountsState>,
    private val eventDelegate: EventDelegate<AccountsEvent>,
) : ViewModel(),
    StateViewModel<AccountListViewModel.AccountsState> by stateDelegate,
    EventViewModel<AccountListViewModel.AccountsEvent> by eventDelegate {
    init {
        stateDelegate.setDefaultState(AccountsState.Idle)
    }
    fun fetchAccounts() {
        stateDelegate.updateState { AccountsState.Loading }
        viewModelScope.launch {
            try {
                val accounts = runtimeAwareSdk.fetchAccounts()
                stateDelegate.updateState {
                    AccountsState.Content(accounts)
                }
            } catch (e: Exception) {
                stateDelegate.updateState { AccountsState.Error(e.message ?: "Unknown error") }
                eventDelegate.sendEvent(AccountsEvent.ShowError(e.message ?: "Failed to fetch accounts."))
            }
        }
    }

    fun deleteAccount(address: String) {
        stateDelegate.updateState { AccountsState.Loading }
        viewModelScope.launch {
            try {
                runtimeAwareSdk.deleteHdKeyAccount(address)
                eventDelegate.sendEvent(AccountsEvent.ShowMessage("Account deleted successfully."))
                fetchAccounts() // Refresh the list after deletion
            } catch (e: Exception) {
                stateDelegate.updateState { AccountsState.Error(e.message ?: "Unknown error") }
                eventDelegate.sendEvent(AccountsEvent.ShowError(e.message ?: "Failed to delete account."))
            }
        }
    }

    sealed interface AccountsState {
        data object Idle : AccountsState

        data object Loading : AccountsState

        data class Content(val accounts: List<LocalAccount>) : AccountsState

        data class Error(val message: String) : AccountsState
    }

    sealed interface AccountsEvent {
        data class ShowError(val message: String) : AccountsEvent

        data class ShowMessage(val message: String) : AccountsEvent
    }
}

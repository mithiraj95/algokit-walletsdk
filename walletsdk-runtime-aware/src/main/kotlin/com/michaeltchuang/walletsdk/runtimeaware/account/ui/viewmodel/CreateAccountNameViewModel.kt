package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core.NameRegistrationPreviewUseCase
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateViewModel
import kotlinx.coroutines.launch

class CreateAccountNameViewModel(
    private val nameRegistrationPreviewUseCase: NameRegistrationPreviewUseCase,
    private val aesPlatformManager: AESPlatformManager,
    private val stateDelegate: StateDelegate<ViewState>,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(),
    StateViewModel<CreateAccountNameViewModel.ViewState> by stateDelegate,
    EventViewModel<CreateAccountNameViewModel.ViewEvent> by eventDelegate {

    init {
        stateDelegate.setDefaultState(ViewState.Idle)
    }

    fun addNewAccount(accountCreation: AccountCreation, customName: String? = null) {
        stateDelegate.onState<ViewState.Idle> {
            stateDelegate.updateState { ViewState.Loading }
            viewModelScope.launch {
                try {
                    accountCreation.customName = customName
                    nameRegistrationPreviewUseCase.addNewAccount(accountCreation)
                    eventDelegate.sendEvent(ViewEvent.FinishedAccountCreation)
                } catch (e: Exception) {
                    displayError(e.message ?: "Unknown error")
                }
            }
        }
    }

    private fun displayError(message: String) {
        viewModelScope.launch {
            eventDelegate.sendEvent(ViewEvent.Error(message))
        }
    }

    sealed interface ViewState {
        data object Idle : ViewState
        data object Loading : ViewState
    }

    sealed interface ViewEvent {
        data object FinishedAccountCreation : ViewEvent
        data class Error(val message: String) : ViewEvent
    }
}

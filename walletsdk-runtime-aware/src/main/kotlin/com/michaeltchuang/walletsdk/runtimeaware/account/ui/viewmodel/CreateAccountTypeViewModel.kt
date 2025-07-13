package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateViewModel
import com.michaeltchuang.walletsdk.runtimeaware.utils.CreationType
import kotlinx.coroutines.launch

class CreateAccountTypeViewModel(
    private val aesPlatformManager: AESPlatformManager,
    private val runtimeAwareSdk: RuntimeAwareSdk,
    private val stateDelegate: StateDelegate<ViewState>,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(),
    StateViewModel<CreateAccountTypeViewModel.ViewState> by stateDelegate,
    EventViewModel<CreateAccountTypeViewModel.ViewEvent> by eventDelegate {

    init {
        stateDelegate.setDefaultState(ViewState.Idle)
    }

    fun createAlgo25Account() {
        stateDelegate.onState<ViewState.Idle> {
            stateDelegate.updateState { ViewState.Loading }
            viewModelScope.launch {
                try {
                    runtimeAwareSdk.createAlgo25Account()?.let {
                        val secretKey = it.secretKey.toByteArray()
                        val accountCreation = AccountCreation(
                            address = it.address,
                            customName = null,
                            isBackedUp = false,
                            type = AccountCreation.Type.Algo25(secretKey),
                            creationType = CreationType.CREATE
                        )
                        eventDelegate.sendEvent(ViewEvent.Algo25AccountCreated(accountCreation))
                    } ?: run {
                        displayError("Failed to create account")
                    }
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
        data class Algo25AccountCreated(val accountCreation: AccountCreation) : ViewEvent
        data class Error(val message: String) : ViewEvent
    }
}

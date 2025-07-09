package com.michaeltchuang.walletsdk.runtimeaware.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.data.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.usecase.NameRegistrationPreviewUseCase
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import kotlinx.coroutines.launch

class CreateAccountNameViewModel(
    private val nameRegistrationPreviewUseCase: NameRegistrationPreviewUseCase,
    private val aesPlatformManager: AESPlatformManager,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(),
    EventViewModel<CreateAccountNameViewModel.ViewEvent> by eventDelegate {

    fun processIntent(intent: CreateAccountNameIntent) {
        when (intent) {
            is CreateAccountNameIntent.AddNewAccount -> {
                viewModelScope.launch {
                    addNewAccount(intent.accountCreation, intent.customName)
                }
            }
        }
    }

    fun addNewAccount(accountCreation: AccountCreation, customName: String? = null) {
        viewModelScope.launch {
            accountCreation.customName = customName // set custom name
            accountCreation.let {
                nameRegistrationPreviewUseCase.addNewAccount(it)
                eventDelegate.sendEvent(ViewEvent.FinishedAccountCreation)
            }
        }
    }

    sealed interface CreateAccountNameIntent {
        data class AddNewAccount(
            val accountCreation: AccountCreation,
            val customName: String? = null
        ) : CreateAccountNameIntent
    }

    sealed interface ViewEvent {
        data object FinishedAccountCreation : ViewEvent
    }

}

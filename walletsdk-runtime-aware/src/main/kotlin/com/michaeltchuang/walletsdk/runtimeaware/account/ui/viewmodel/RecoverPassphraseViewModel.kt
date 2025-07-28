package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.OnboardingAccountType
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.recoverypassphrase.RecoverPassphraseUseCase
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecoverPassphraseViewModel(
    private val recoverPassphrasePreviewUseCase: RecoverPassphraseUseCase,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(), EventViewModel<RecoverPassphraseViewModel.ViewEvent> by eventDelegate {
    fun onRecoverButtonClick(
        mnemonic: String, onboardingAccountType: OnboardingAccountType
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            recoverPassphrasePreviewUseCase.validateEnteredMnemonics(
                mnemonic, onboardingAccountType
            ).collectLatest { accountCreation ->
                accountCreation?.let {
                    eventDelegate.sendEvent(ViewEvent.NavigateToAccountNameScreen(it))
                }
            }
        }
    }

    interface ViewEvent {
        data class NavigateToAccountNameScreen(val accountCreation: AccountCreation) : ViewEvent
    }
}

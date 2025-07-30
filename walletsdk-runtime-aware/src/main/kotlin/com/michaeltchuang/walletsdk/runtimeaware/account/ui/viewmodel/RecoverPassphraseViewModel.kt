package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.OnboardingAccountType
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.recoverypassphrase.RecoverPassphraseUseCase
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.utils.splitMnemonic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecoverPassphraseViewModel(
    private val context: Application,
    private val recoverPassphrasePreviewUseCase: RecoverPassphraseUseCase,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(), EventViewModel<RecoverPassphraseViewModel.ViewEvent> by eventDelegate {
    fun onRecoverAccount(
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

    fun onClipBoardPastedMnemonic(mnemonic: String, isValid: () -> Unit) {
        val splittedText = mnemonic.splitMnemonic()
        if (
            splittedText.size != OnboardingAccountType.Algo25.wordCount &&
            splittedText.size != OnboardingAccountType.HdKey.wordCount
        ) {
            viewModelScope.launch {
                eventDelegate.sendEvent(ViewEvent.ShowError(context.getString(R.string.the_last_copied_text)))
            }
        } else {
            isValid()
        }

    }

    interface ViewEvent {
        data class NavigateToAccountNameScreen(val accountCreation: AccountCreation) : ViewEvent
        data class ShowError(val error: String) : ViewEvent
    }
}

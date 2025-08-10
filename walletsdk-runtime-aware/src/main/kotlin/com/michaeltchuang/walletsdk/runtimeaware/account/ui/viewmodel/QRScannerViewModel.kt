package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel.QRScannerViewModel.ViewEvent.NavigateToRecoveryPhraseScreen
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.DeeplinkHandler
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QRScannerViewModel(
    private val deeplinkHandler: DeeplinkHandler,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(), EventViewModel<QRScannerViewModel.ViewEvent> by eventDelegate {

    init {
        viewModelScope.launch {
            deeplinkHandler.deepLinkState.collect {
                when (it) {
                    is DeeplinkHandler.DeepLinkState.OnImportAccountDeepLink -> {
                        eventDelegate.sendEvent(NavigateToRecoveryPhraseScreen(it.mnemonic))
                    }

                    is DeeplinkHandler.DeepLinkState.KeyReg -> {
                        eventDelegate.sendEvent(
                            ViewEvent.NavigateToTransactionSignatureRequestScreen(
                                it.keyReg
                            )
                        )
                    }

                    is DeeplinkHandler.DeepLinkState.OnUnrecognizedDeepLink -> {
                        eventDelegate.sendEvent(ViewEvent.ShowUnrecognizedDeeplink)
                    }
                }
            }
        }
    }

    fun handleDeeplink(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deeplinkHandler.handleDeepLink(uri)
        }
    }

    interface ViewEvent {
        data class NavigateToRecoveryPhraseScreen(val mnemonic: String) : ViewEvent
        data class NavigateToTransactionSignatureRequestScreen(val keyReg: DeepLink.KeyReg) :
            ViewEvent

        object ShowUnrecognizedDeeplink : ViewEvent
    }
}

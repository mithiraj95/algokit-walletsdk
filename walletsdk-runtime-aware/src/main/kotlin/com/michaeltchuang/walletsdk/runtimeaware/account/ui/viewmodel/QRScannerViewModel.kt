package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.DeeplinkHandler
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
                        eventDelegate.sendEvent(ViewEvent.NavigateToRecoveryPharaseScreen(it.mnemonic))
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
        data class NavigateToRecoveryPharaseScreen(val mnemonic: String) : ViewEvent
        data class ShowKeyRegDeeplinkError(val address: String) : ViewEvent
    }

}

package com.michaeltchuang.walletsdk.runtimeaware.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.data.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.utils.CreationType

class CreateAccountTypeViewModel(
    private val aesPlatformManager: AESPlatformManager,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(),
    EventViewModel<CreateAccountTypeViewModel.ViewEvent> by eventDelegate {

    suspend fun createAlgo25Account(runtimeAwareSdk: RuntimeAwareSdk) {

        runtimeAwareSdk.createAlgo25Account()?.let {
            val secretKey = it.secretKey.toByteArray()
            val accountCreation = AccountCreation(
                address = it.address,
                customName = null,
                isBackedUp = false,
                type = AccountCreation.Type.Algo25(secretKey),
                creationType = CreationType.CREATE
            )
            eventDelegate.sendEvent(viewModelScope, ViewEvent.Algo25AccountCreated(accountCreation))
        } ?: {
            eventDelegate.sendEvent(viewModelScope, ViewEvent.Error("Failed "))
        }
    }

    sealed interface ViewEvent {
        data class Algo25AccountCreated(val accountCreation: AccountCreation) : ViewEvent
        data class Error(val message: String) : ViewEvent
    }


}


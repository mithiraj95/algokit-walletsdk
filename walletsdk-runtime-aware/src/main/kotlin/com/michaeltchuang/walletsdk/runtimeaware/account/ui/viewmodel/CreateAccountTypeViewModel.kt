package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.utils.CreationType
import kotlinx.coroutines.launch

class CreateAccountTypeViewModel(
    private val aesPlatformManager: AESPlatformManager,
    private val runtimeAwareSdk: RuntimeAwareSdk,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(),
    EventViewModel<CreateAccountTypeViewModel.ViewEvent> by eventDelegate {

    fun processIntent(intent: CreateAccountIntent) {
        when (intent) {
            CreateAccountIntent.CreateAlgo25Account -> {
                viewModelScope.launch {
                    createAlgo25Account()
                }
            }
        }
    }

    suspend fun createAlgo25Account() {
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

    sealed interface CreateAccountIntent {
        data object CreateAlgo25Account : CreateAccountIntent
    }

    sealed interface ViewEvent {
        data class Algo25AccountCreated(val accountCreation: AccountCreation) : ViewEvent
        data class Error(val message: String) : ViewEvent
    }


}


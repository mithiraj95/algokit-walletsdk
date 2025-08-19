package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.AccountCreationHdKeyTypeMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.local.HdSeedRepository
import com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.model.HdKeyAddressIndex
import com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.sdk.AlgorandBip39WalletProvider
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.usecase.AndroidEncryptionManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateViewModel
import com.michaeltchuang.walletsdk.runtimeaware.utils.CreationType
import kotlinx.coroutines.launch

class OnboardingAccountTypeViewModel(
    private val androidEncryptionManager: AndroidEncryptionManager,
    private val aesPlatformManager: AESPlatformManager,
    private val runtimeAwareSdk: RuntimeAwareSdk,
    private val accountCreationHdKeyTypeMapper: AccountCreationHdKeyTypeMapper,
    private val hdSeedRepository: HdSeedRepository,
    private val algorandBip39WalletProvider: AlgorandBip39WalletProvider,
    private val stateDelegate: StateDelegate<ViewState>,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(),
    StateViewModel<OnboardingAccountTypeViewModel.ViewState> by stateDelegate,
    EventViewModel<OnboardingAccountTypeViewModel.ViewEvent> by eventDelegate {

    init {
        stateDelegate.setDefaultState(ViewState.Loading)
        viewModelScope.launch { androidEncryptionManager.initializeEncryptionManager() }
        hasAnySeedExist()
    }

    private fun hasAnySeedExist() {
        viewModelScope.launch {
            hdSeedRepository.hasAnySeed().let { hasAnySeed ->
                stateDelegate.updateState {
                    ViewState.Content(hasAnySeed)
                }
            }
        }
    }

    fun createHdKeyAccount() {
        viewModelScope.launch {
            val wallet = algorandBip39WalletProvider.createBip39Wallet()
            val hdKeyAddress = wallet.generateAddress(HdKeyAddressIndex(0, 0, 0))
            val hdKeyType =
                accountCreationHdKeyTypeMapper(
                    wallet.getEntropy().value,
                    hdKeyAddress,
                    seedId = null
                )
            val accountCreation = AccountCreation(
                address = hdKeyAddress.address,
                customName = null,
                isBackedUp = false,
                type = hdKeyType,
                creationType = CreationType.CREATE
            )
            eventDelegate.sendEvent(ViewEvent.AccountCreated(accountCreation))
        }
    }

    fun createAlgo25Account() {
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
                    eventDelegate.sendEvent(ViewEvent.AccountCreated(accountCreation))
                } ?: run {
                    displayError("Failed to create account")
                }
            } catch (e: Exception) {
                displayError(e.message ?: "Unknown error")
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
        data class Content(val hasAnySeed: Boolean) : ViewState
    }

    sealed interface ViewEvent {
        data class AccountCreated(val accountCreation: AccountCreation) : ViewEvent
        data class Error(val message: String) : ViewEvent
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.account.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.account.data.mapper.entity.AccountCreationHdKeyTypeMapper
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.model.core.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.GetHdEntropy
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.local.GetHdWalletSummaries
import com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.model.HdKeyAddressIndex
import com.michaeltchuang.walletsdk.runtimeaware.algosdk.bip39.sdk.AlgorandBip39WalletProvider
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.EventViewModel
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateDelegate
import com.michaeltchuang.walletsdk.runtimeaware.foundation.StateViewModel
import com.michaeltchuang.walletsdk.runtimeaware.utils.CreationType
import com.michaeltchuang.walletsdk.runtimeaware.utils.clearFromMemory
import kotlinx.coroutines.launch

class HDWalletSelectionViewModel(
    private val aesPlatformManager: AESPlatformManager,
    private val getHdWalletSummaries: GetHdWalletSummaries,
    private val accountCreationHdKeyTypeMapper: AccountCreationHdKeyTypeMapper,
    private val getHdEntropy: GetHdEntropy,
    private val algorandBip39WalletProvider: AlgorandBip39WalletProvider,
    private val stateDelegate: StateDelegate<ViewState>,
    private val eventDelegate: EventDelegate<ViewEvent>,
) : ViewModel(),
    StateViewModel<HDWalletSelectionViewModel.ViewState> by stateDelegate,
    EventViewModel<HDWalletSelectionViewModel.ViewEvent> by eventDelegate {

    init {
        stateDelegate.setDefaultState(ViewState.Idle)
        loadLocalWallets()
    }

    fun loadLocalWallets() {
        stateDelegate.updateState { ViewState.Loading }
        viewModelScope.launch {
            val walletItemPreviews = getHdWalletSummaries()?.map {
                WalletItemPreview(
                    seedId = it.seedId,
                    name = "Wallet #${it.seedId}",
                    numberOfAccounts = "${it.accountCount} account",
                    primaryValue = it.primaryValue,
                    secondaryValue = it.secondaryValue,
                    maxAccountIndex = it.maxAccountIndex
                )
            }.orEmpty()
            stateDelegate.updateState {
                ViewState.Content(
                    walletItemPreviews = walletItemPreviews,
                )
            }
        }
    }

    fun createNewHdAccount(seedId: Int, maxAccountIndex: Int) {
        viewModelScope.launch {
            val entropy = getHdEntropy(seedId) ?: return@launch
            val nextHdAccountIndex = maxAccountIndex + 1
            val wallet = algorandBip39WalletProvider.getBip39Wallet(entropy)
            val index = HdKeyAddressIndex(nextHdAccountIndex, changeIndex = 0, keyIndex = 0)
            val hdKeyAddress = wallet.generateAddress(index)
            val accountCreation = AccountCreation(
                address = hdKeyAddress.address,
                customName = null,
                isBackedUp = false,
                type = accountCreationHdKeyTypeMapper(entropy, hdKeyAddress, seedId),
                creationType = CreationType.CREATE
            )
            entropy.clearFromMemory()
            wallet.invalidate()
            eventDelegate.sendEvent(
                ViewEvent.AccountCreated(
                    accountCreation
                )
            )
        }
    }

    fun createHdKeyAccount() {
        viewModelScope.launch {
            val wallet = algorandBip39WalletProvider.createBip39Wallet()
            val hdKeyAddress = wallet.generateAddress(HdKeyAddressIndex())
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
            eventDelegate.sendEvent(
                ViewEvent.AccountCreated(
                    accountCreation
                )
            )
        }
    }

    private fun displayError(message: String) {
        viewModelScope.launch {
            eventDelegate.sendEvent(ViewEvent.Error(message))
        }
    }

    data class WalletItemPreview(
        val seedId: Int,
        val name: String,
        val numberOfAccounts: String,
        val primaryValue: String,
        val secondaryValue: String,
        val maxAccountIndex: Int
    )

    sealed interface ViewState {
        data object Idle : ViewState
        data object Loading : ViewState
        data class Content(
            val walletItemPreviews: List<WalletItemPreview> = emptyList(),
        ) : ViewState

        data class Error(val message: String) : ViewState
    }

    sealed interface ViewEvent {
        data class AccountCreated(val accountCreation: AccountCreation) : ViewEvent
        data class Error(val message: String) : ViewEvent
    }
}

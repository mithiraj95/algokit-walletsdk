package com.michaeltchuang.walletsdk.runtimeaware.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michaeltchuang.walletsdk.runtimeaware.RuntimeAwareSdk
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.data.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.NameRegistrationPreviewUseCase
import com.michaeltchuang.walletsdk.runtimeaware.account.local.domain.model.LocalAccount
import com.michaeltchuang.walletsdk.runtimeaware.encryption.domain.manager.AESPlatformManager
import com.michaeltchuang.walletsdk.runtimeaware.utils.CreationType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NameRegistrationViewModel(
    private val nameRegistrationPreviewUseCase: NameRegistrationPreviewUseCase,
    private val aesPlatformManager: AESPlatformManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<LocalAccount.Algo25>>(emptyList())
    val uiState: StateFlow<List<LocalAccount.Algo25>> = _uiState.asStateFlow()

    var accountCreation: AccountCreation? = null


    fun addNewAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            accountCreation?.let { nameRegistrationPreviewUseCase.addNewAccount(it) }
            getAccount()
        }
    }

    suspend fun getAccount() {
        _uiState.value = nameRegistrationPreviewUseCase.getAccount()
    }

    suspend fun deleteAccount(address: String) {
        nameRegistrationPreviewUseCase.deleteAccount(address)
        getAccount()
    }


    suspend fun createAlgo25Account(runtimeAwareSdk: RuntimeAwareSdk): AccountCreation? {
        val account = runtimeAwareSdk.createAlgo25Account()

        if (account != null) {
            val secretKey = account.secretKey.toByteArray()
            accountCreation = AccountCreation(
                address = account.address,
                customName = null,
                isBackedUp = false,
                type = AccountCreation.Type.Algo25(secretKey),
                creationType = CreationType.CREATE
            )
        }

        return accountCreation
    }
}

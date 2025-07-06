package com.michaeltchuang.walletsdk.runtimeaware.account.domain.data

import com.michaeltchuang.walletsdk.runtimeaware.utils.Event

data class NameRegistrationPreview(
    val handleNextNavigationEvent: Event<Unit?>?,
    private val accountAlreadyExistsEvent: Event<Unit?>?,
    private val createAccountEvent: Event<AccountCreation>?,
    private val updateWatchAccountEvent: Event<AccountCreation>?,
    private val walletId: Int? = null
) {
    fun getAccountAlreadyExistsEvent(): Event<Unit?>? {
        if (accountAlreadyExistsEvent?.consumed == false) {
            createAccountEvent?.consume()
            updateWatchAccountEvent?.consume()
        }
        return accountAlreadyExistsEvent
    }

    fun getCreateAccountEvent(): Event<AccountCreation>? {
        if (createAccountEvent?.consumed == false) {
            accountAlreadyExistsEvent?.consume()
            updateWatchAccountEvent?.consume()
        }
        return createAccountEvent
    }

    fun getUpdateWatchAccountEvent(): Event<AccountCreation>? {
        if (updateWatchAccountEvent?.consumed == false) {
            accountAlreadyExistsEvent?.consume()
            createAccountEvent?.consume()
        }
        return updateWatchAccountEvent
    }

    fun getWalletId(): Int? {
        return walletId
    }
}

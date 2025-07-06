package com.michaeltchuang.walletsdk.runtimeaware.account.mapper

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.data.AccountCreation
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.data.NameRegistrationPreview
import com.michaeltchuang.walletsdk.runtimeaware.utils.Event


class NameRegistrationPreviewMapper {

    fun mapToInitialPreview(walletId: Int? = null): NameRegistrationPreview {
        return NameRegistrationPreview(
            accountAlreadyExistsEvent = null,
            updateWatchAccountEvent = null,
            createAccountEvent = null,
            handleNextNavigationEvent = null,
            walletId = walletId
        )
    }

    fun mapToCreateAccountPreview(
        accountCreation: AccountCreation,
        walletId: Int?
    ): NameRegistrationPreview {
        return NameRegistrationPreview(
            accountAlreadyExistsEvent = null,
            updateWatchAccountEvent = null,
            createAccountEvent = Event(accountCreation),
            handleNextNavigationEvent = null,
            walletId = walletId
        )
    }

    fun mapToUpdateWatchAccountPreview(accountCreation: AccountCreation): NameRegistrationPreview {
        return NameRegistrationPreview(
            accountAlreadyExistsEvent = null,
            updateWatchAccountEvent = Event(accountCreation),
            createAccountEvent = null,
            handleNextNavigationEvent = null
        )
    }

    fun mapToAccountAlreadyExistsPreview(): NameRegistrationPreview {
        return NameRegistrationPreview(
            accountAlreadyExistsEvent = Event(Unit),
            updateWatchAccountEvent = null,
            createAccountEvent = null,
            handleNextNavigationEvent = null,
            walletId = null
        )
    }

    fun mapToWatchAccountUpdatedPreview(): NameRegistrationPreview {
        return NameRegistrationPreview(
            accountAlreadyExistsEvent = null,
            updateWatchAccountEvent = null,
            createAccountEvent = null,
            handleNextNavigationEvent = Event(Unit),
            walletId = null
        )
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase.core

import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.core.RegistrationRepository

class RegistrationUseCase(
    private val registrationRepository: RegistrationRepository
) {

    fun setRegistrationSkipPreferenceAsSkipped() {
        registrationRepository.setRegistrationSkipPreferenceAsSkipped()
    }

    fun getRegistrationSkipped(): Boolean {
        return registrationRepository.getRegistrationSkipped()
    }
}

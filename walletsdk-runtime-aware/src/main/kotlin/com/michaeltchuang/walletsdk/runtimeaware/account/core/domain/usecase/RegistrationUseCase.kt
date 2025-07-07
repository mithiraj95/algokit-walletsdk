package com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.usecase

import com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.repository.RegistrationRepository

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

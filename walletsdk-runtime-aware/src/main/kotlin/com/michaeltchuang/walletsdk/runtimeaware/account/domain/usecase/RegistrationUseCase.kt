package com.michaeltchuang.walletsdk.runtimeaware.account.domain.usecase

import com.michaeltchuang.walletsdk.runtimeaware.account.repository.RegistrationRepository


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

package com.michaeltchuang.walletsdk.runtimeaware.account.data.repository

import com.michaeltchuang.walletsdk.runtimeaware.account.data.sharedpref.RegistrationSkipLocalSource
import com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.core.RegistrationRepository

class RegistrationRepositoryImpl(
    private val registrationSkipLocalSource: RegistrationSkipLocalSource
) : RegistrationRepository {

    override fun setRegistrationSkipPreferenceAsSkipped() {
        registrationSkipLocalSource.saveData(true)
    }

    override fun getRegistrationSkipped(): Boolean {
        return registrationSkipLocalSource.getData(RegistrationSkipLocalSource.Companion.defaultRegisterSkipPreference)
    }
}
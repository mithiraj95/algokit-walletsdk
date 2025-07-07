package com.michaeltchuang.walletsdk.runtimeaware.account.core.data.repository

import com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.repository.RegistrationRepository
import com.michaeltchuang.walletsdk.runtimeaware.ui.data.sharedpref.RegistrationSkipLocalSource

class RegistrationRepositoryImpl(
    private val registrationSkipLocalSource: RegistrationSkipLocalSource
) : RegistrationRepository {

    override fun setRegistrationSkipPreferenceAsSkipped() {
        registrationSkipLocalSource.saveData(true)
    }

    override fun getRegistrationSkipped(): Boolean {
        return registrationSkipLocalSource.getData(RegistrationSkipLocalSource.defaultRegisterSkipPreference)
    }
}

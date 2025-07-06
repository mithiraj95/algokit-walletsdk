package com.michaeltchuang.walletsdk.runtimeaware.account.repository

import com.michaeltchuang.walletsdk.runtimeaware.account.sharedpref.RegistrationSkipLocalSource


class RegistrationRepository(
    private val registrationSkipLocalSource: RegistrationSkipLocalSource
) {

    fun setRegistrationSkipPreferenceAsSkipped() {
        registrationSkipLocalSource.saveData(true)
    }

    fun getRegistrationSkipped(): Boolean {
        return registrationSkipLocalSource.getData(RegistrationSkipLocalSource.defaultRegisterSkipPreference)
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.account.core.domain.repository

interface RegistrationRepository {

    fun setRegistrationSkipPreferenceAsSkipped()

    fun getRegistrationSkipped(): Boolean
}
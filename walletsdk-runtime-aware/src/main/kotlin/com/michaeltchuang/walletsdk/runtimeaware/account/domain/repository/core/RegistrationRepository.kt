package com.michaeltchuang.walletsdk.runtimeaware.account.domain.repository.core

interface RegistrationRepository {

    fun setRegistrationSkipPreferenceAsSkipped()

    fun getRegistrationSkipped(): Boolean
}
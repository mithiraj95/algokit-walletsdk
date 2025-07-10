package com.michaeltchuang.walletsdk.runtimeaware.account.data.sharedpref

import android.content.SharedPreferences
import com.michaeltchuang.walletsdk.runtimeaware.foundation.sharedpref.SharedPrefLocalSource

class RegistrationSkipLocalSource(
    sharedPreferences: SharedPreferences
) : SharedPrefLocalSource<Boolean>(sharedPreferences) {

    override val key: String
        get() = REGISTER_SKIP_KEY

    override fun getData(defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defaultValue)
    }

    override fun getDataOrNull(): Boolean {
        return sharedPref.getBoolean(key, defaultRegisterSkipPreference)
    }

    override fun saveData(data: Boolean) {
        saveData { it.putBoolean(key, data) }
    }

    companion object {
        private const val REGISTER_SKIP_KEY = "register_skip_key"
        const val defaultRegisterSkipPreference = false
    }
}

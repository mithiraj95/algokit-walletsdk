package com.michaeltchuang.walletsdk.runtimeenabled.foundation.security

internal interface SecurityProvidersFactory {
    fun getProviders(): List<SecurityProvider>
}

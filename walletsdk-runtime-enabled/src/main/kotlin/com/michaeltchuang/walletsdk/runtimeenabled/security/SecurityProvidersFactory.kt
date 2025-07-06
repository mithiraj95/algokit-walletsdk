package com.michaeltchuang.walletsdk.runtimeenabled.security

internal interface SecurityProvidersFactory {
    fun getProviders(): List<SecurityProvider>
}

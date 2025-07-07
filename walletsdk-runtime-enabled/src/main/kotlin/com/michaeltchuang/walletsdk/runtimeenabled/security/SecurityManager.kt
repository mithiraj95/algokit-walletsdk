package com.michaeltchuang.walletsdk.runtimeenabled.security

internal interface SecurityManager {
    fun registerProvider(securityProvider: SecurityProvider)
}

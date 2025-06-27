package com.michaeltchuang.walletsdk.runtimeenabled.foundation.security

internal interface SecurityManager {
    fun registerProvider(securityProvider: SecurityProvider)
}

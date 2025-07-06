package com.michaeltchuang.walletsdk.runtimeenabled.security

import java.security.Provider

data class SecurityProvider(
    val provider: Provider,
    val priority: Int
)

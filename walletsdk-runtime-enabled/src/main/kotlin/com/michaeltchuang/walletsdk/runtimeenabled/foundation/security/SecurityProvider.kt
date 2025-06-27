package com.michaeltchuang.walletsdk.runtimeenabled.foundation.security

import java.security.Provider

data class SecurityProvider(
    val provider: Provider,
    val priority: Int
)

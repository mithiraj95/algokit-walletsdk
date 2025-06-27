package com.michaeltchuang.walletsdk.runtimeenabled.foundation.security

import java.security.Security

internal class SecurityManagerImpl : SecurityManager {

    override fun registerProvider(securityProvider: SecurityProvider) {
        Security.removeProvider(securityProvider.provider.name)
        Security.insertProviderAt(securityProvider.provider, securityProvider.priority)
    }
}

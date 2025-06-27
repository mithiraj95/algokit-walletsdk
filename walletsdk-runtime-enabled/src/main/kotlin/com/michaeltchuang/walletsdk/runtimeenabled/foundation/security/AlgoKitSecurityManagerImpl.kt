package com.michaeltchuang.walletsdk.runtimeenabled.foundation.security

internal class AlgoKitSecurityManagerImpl(
    private val securityManager: SecurityManager,
    private val securityProvidersFactory: SecurityProvidersFactory
) : AlgoKitSecurityManager {

    override fun initializeSecurityManager() {
        val securityProviders = securityProvidersFactory.getProviders()
        securityProviders.forEach { provider ->
            securityManager.registerProvider(provider)
        }
    }
}

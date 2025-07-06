package com.michaeltchuang.walletsdk.runtimeenabled.security

import org.bouncycastle.jce.provider.BouncyCastleProvider

internal class SecurityProvidersFactoryImpl : SecurityProvidersFactory {

    override fun getProviders(): List<SecurityProvider> {
        return listOf(
            getBouncyCastleProvider()
        )
    }

    private fun getBouncyCastleProvider(): SecurityProvider {
        return SecurityProvider(
            provider = BouncyCastleProvider(),
            priority = 0
        )
    }
}

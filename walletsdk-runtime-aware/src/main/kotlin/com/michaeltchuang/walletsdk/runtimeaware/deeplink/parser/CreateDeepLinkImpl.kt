package com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder.DeepLinkBuilder
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink

internal class CreateDeepLinkImpl(
    private val parseDeepLinkPayload: ParseDeepLinkPayload,
    private val mnemonicDeepLinkBuilder: DeepLinkBuilder,
) : CreateDeepLink {

    override fun invoke(url: String): DeepLink {
        val payload = parseDeepLinkPayload(url)

        return when {

            mnemonicDeepLinkBuilder.doesDeeplinkMeetTheRequirements(payload) -> {
                mnemonicDeepLinkBuilder.createDeepLink(payload)
            }

            else -> DeepLink.Undefined(url)
        }


    }
}

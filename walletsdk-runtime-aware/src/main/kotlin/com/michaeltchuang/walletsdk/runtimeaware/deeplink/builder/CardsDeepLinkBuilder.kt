package com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLinkPayload

internal class CardsDeepLinkBuilder : DeepLinkBuilder {

    override fun doesDeeplinkMeetTheRequirements(payload: DeepLinkPayload): Boolean {
        return with(payload) {
            host == CARDS_HOST_NAME && path != null
        }
    }

    override fun createDeepLink(payload: DeepLinkPayload): DeepLink {
        return DeepLink.Cards(path = payload.path.orEmpty())
    }

    companion object {
        private const val CARDS_HOST_NAME = "cards"
    }
}

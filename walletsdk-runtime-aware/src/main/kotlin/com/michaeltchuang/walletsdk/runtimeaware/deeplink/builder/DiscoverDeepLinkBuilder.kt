package com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLinkPayload

internal class DiscoverDeepLinkBuilder : DeepLinkBuilder {

    override fun doesDeeplinkMeetTheRequirements(payload: DeepLinkPayload): Boolean {
        return with(payload) {
            host == DISCOVER_HOST_NAME && path != null
        }
    }

    override fun createDeepLink(payload: DeepLinkPayload): DeepLink {
        return DeepLink.Discover(path = payload.path.orEmpty())
    }

    companion object {
        private const val DISCOVER_HOST_NAME = "discover"
    }
}

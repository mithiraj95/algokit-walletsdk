package com.michaeltchuang.walletsdk.runtimeaware.deeplink.builder

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLinkPayload

internal interface DeepLinkBuilder {
    fun doesDeeplinkMeetTheRequirements(payload: DeepLinkPayload): Boolean

    fun createDeepLink(payload: DeepLinkPayload): DeepLink
}

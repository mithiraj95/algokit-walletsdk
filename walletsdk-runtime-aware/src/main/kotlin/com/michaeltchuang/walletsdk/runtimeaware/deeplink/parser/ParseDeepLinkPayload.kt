package com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLinkPayload

internal interface ParseDeepLinkPayload {

    operator fun invoke(url: String): DeepLinkPayload
}

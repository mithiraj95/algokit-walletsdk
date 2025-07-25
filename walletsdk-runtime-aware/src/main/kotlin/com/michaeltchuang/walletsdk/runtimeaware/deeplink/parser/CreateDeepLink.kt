package com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.DeepLink

interface CreateDeepLink {
    operator fun invoke(url: String): DeepLink
}

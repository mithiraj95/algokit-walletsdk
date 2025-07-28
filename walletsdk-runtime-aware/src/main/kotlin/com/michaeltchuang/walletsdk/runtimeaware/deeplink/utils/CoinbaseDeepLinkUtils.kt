package com.michaeltchuang.walletsdk.runtimeaware.deeplink.utils

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.PeraUri

private const val COINBASE_DEEPLINK_ROOT = "algo:"

internal fun isCoinbaseDeepLink(uri: PeraUri): Boolean {
    return uri.rawUri.startsWith(COINBASE_DEEPLINK_ROOT, ignoreCase = true)
}

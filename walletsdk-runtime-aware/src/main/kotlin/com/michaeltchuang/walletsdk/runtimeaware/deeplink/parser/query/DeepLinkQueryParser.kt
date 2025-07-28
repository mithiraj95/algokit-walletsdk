package com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.query

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.PeraUri

internal interface DeepLinkQueryParser<T> {
    fun parseQuery(peraUri: PeraUri): T
}

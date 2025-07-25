package com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser

import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.PeraUri

internal interface PeraUriParser {
    fun parseUri(uri: String): PeraUri
}

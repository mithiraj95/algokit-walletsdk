package com.michaeltchuang.walletsdk.runtimeenabled.foundation.network.exceptions

data class ParsedError(
    val keyErrorMessageMap: Map<String, List<String>>,
    val message: String,
    val responseCode: Int
)

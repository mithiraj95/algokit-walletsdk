package com.michaeltchuang.walletsdk.runtimeaware.deeplink.model

internal data class PeraUri(
    val scheme: String?,
    val host: String?,
    val path: String?,
    val queryParams: Map<String, String?>,
    val fragment: String?,
    val rawUri: String
) {

    fun isAppLink(): Boolean {
        return host?.startsWith(PERAWALLET_APPLINK_AUTH_KEY) ?: false
    }

    fun getQueryParam(key: String): String? {
        return queryParams[key]
    }

    private companion object {
        const val PERAWALLET_APPLINK_AUTH_KEY = "perawallet.app"
    }
}

package com.michaeltchuang.walletsdk.runtimeaware.deeplink.parser.query

import com.google.gson.annotations.SerializedName
import com.michaeltchuang.walletsdk.runtimeaware.deeplink.model.PeraUri
import com.michaeltchuang.walletsdk.runtimeaware.foundation.json.JsonSerializer

internal class MnemonicQueryParser(
    private val jsonSerializer: JsonSerializer
) : DeepLinkQueryParser<String?> {

    override fun parseQuery(peraUri: PeraUri): String? {
        return try {
            jsonSerializer.fromJson(peraUri.rawUri, MnemonicPayload::class.java)?.mnemonic
        } catch (e: Exception) {
            null
        }
    }

    internal data class MnemonicPayload(
        @SerializedName("version") val version: Double? = null,
        @SerializedName("mnemonic") val mnemonic: String
    )
}

package com.michaeltchuang.walletsdk.runtimeaware.utils

import java.util.Base64

fun ByteArray.clearFromMemory(): ByteArray {
    // Overwrite the byte array contents with zeros
    this.fill(0)
    return ByteArray(0)
}

fun ByteArray.base64EncodeToString(): String {
    return Base64.getEncoder().encodeToString(this)
}

fun String.base64DecodeToByteArray(): ByteArray {
    return Base64.getDecoder().decode(this)
}

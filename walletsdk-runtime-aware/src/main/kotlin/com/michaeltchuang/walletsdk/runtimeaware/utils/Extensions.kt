package com.michaeltchuang.walletsdk.runtimeaware.utils

import android.content.ClipDescription
import android.content.ClipDescription.MIMETYPE_TEXT_HTML
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
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

fun Context.getTextFromClipboard(): String? {
    val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java) ?: return ""
    return clipboard.getTextFromClipboard()
}
fun ClipboardManager.getTextFromClipboard(): String? {
    val firstClip = primaryClip?.getItemAt(0)?.text?.toString().orEmpty()
    return when (getClipboardMimeType(primaryClipDescription)) {
        MIMETYPE_TEXT_PLAIN -> firstClip
        MIMETYPE_TEXT_HTML -> HtmlCompat.fromHtml(firstClip, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
        else -> null
    }
}

private fun getClipboardMimeType(clipDescription: ClipDescription?): String? {
    return clipDescription?.let { safeClipDescription ->
        when {
            safeClipDescription.hasMimeType(MIMETYPE_TEXT_HTML) -> MIMETYPE_TEXT_HTML
            safeClipDescription.hasMimeType(MIMETYPE_TEXT_PLAIN) -> MIMETYPE_TEXT_PLAIN
            else -> null
        }
    }
}
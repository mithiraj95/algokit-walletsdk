package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState


@Composable
fun AlgoKitWebViewPlatformScreen(url: String) {
    Box {
        WebView(rememberWebViewState(url = url), modifier = Modifier.fillMaxSize())
    }
}

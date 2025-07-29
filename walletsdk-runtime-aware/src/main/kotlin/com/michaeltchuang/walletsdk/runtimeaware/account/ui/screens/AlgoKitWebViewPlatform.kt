package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.final_class.webview_multiplatform_mobile.webview.WebViewPlatform
import com.final_class.webview_multiplatform_mobile.webview.controller.rememberWebViewController
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState


@Composable
fun AlgoKitWebViewPlatformScreen(url: String, navController: NavController) {
    val webViewController by rememberWebViewController()
    Column {
        AlgoKitTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black),
            onClick = { navController.popBackStack() }
        )
        WebView(rememberWebViewState(url= url), modifier = Modifier.fillMaxSize())
     /*   WebViewPlatform(webViewController = webViewController)
        LaunchedEffect(Unit) {
            webViewController.open(url)
        }*/
    }
}

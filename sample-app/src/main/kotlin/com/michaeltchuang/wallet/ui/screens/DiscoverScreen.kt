package com.michaeltchuang.wallet.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.michaeltchuang.wallet.ui.widgets.snackbar.SnackbarViewModel
import com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens.AlgoKitWebViewPlatformScreen
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.utils.WalletSdkConstants

@Composable
fun DiscoverScreen(
    navController: NavController,
    snackbarViewModel: SnackbarViewModel,
    tag: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier =
            Modifier
                .fillMaxSize()
                .background(AlgoKitTheme.colors.background),
    ) {
        AlgoKitWebViewPlatformScreen(WalletSdkConstants.REPO_URL)
    }
}

package com.michaeltchuang.wallet.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = AlgoKitTheme.colors.background,
                titleContentColor = AlgoKitTheme.colors.textMain,
            ),
        title = {
            Text(
                "AlgoKit Wallet",
                maxLines = 1,
            )
        },
    )
}

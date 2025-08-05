package com.michaeltchuang.wallet.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val scop = rememberCoroutineScope()
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
        actions = {
            Icon(
                modifier =
                    Modifier
                        .size(55.dp)
                        .padding(horizontal = 8.dp)
                        .clickable(onClick = {
                            scop.launch {
                                QREvent.qrClickEvent.emit(true)
                            }
                        }),
                painter = painterResource(com.michaeltchuang.wallet.R.drawable.ic_qr_scan),
                contentDescription = "qr",
                tint = AlgoKitTheme.colors.textMain,
            )
        },
    )
}

object QREvent {
    val qrClickEvent = MutableSharedFlow<Boolean>()
}

@PreviewLightDark
@Composable
fun TopBarPreview() {
    AlgoKitTheme {
    }
}

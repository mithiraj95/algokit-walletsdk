package com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme

@Composable
fun AlgoKitButtonIcon(@DrawableRes iconResId: Int) {
    Icon(
        modifier = Modifier.size(24.dp),
        painter = painterResource(iconResId),
        contentDescription = null,
        tint = AlgoKitTheme.colors.buttonPrimaryText
    )
}
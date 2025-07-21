package com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@Composable
fun AlgoKitBackArrowButtonIcon(modifier: Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Icon(
            tint = AlgoKitTheme.colors.textMain,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}
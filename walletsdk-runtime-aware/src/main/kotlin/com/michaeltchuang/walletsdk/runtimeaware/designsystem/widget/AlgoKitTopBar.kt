package com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme

@Composable
fun AlgoKitTopBar(modifier: Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(
            tint = AlgoKitTheme.colors.textMain,
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back"
        )
    }
}
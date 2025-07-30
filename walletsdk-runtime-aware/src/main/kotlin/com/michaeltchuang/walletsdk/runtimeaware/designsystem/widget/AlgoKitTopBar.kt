package com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme

@Composable
fun AlgoKitTopBar(modifier: Modifier = Modifier ,onClick: () -> Unit) {
    Box(
        modifier = modifier
            .height(50.dp)
    ) {
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(24.dp)
                .align(alignment = Alignment.CenterStart)
        ) {
            Icon(
                tint = AlgoKitTheme.colors.textMain,
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }

}
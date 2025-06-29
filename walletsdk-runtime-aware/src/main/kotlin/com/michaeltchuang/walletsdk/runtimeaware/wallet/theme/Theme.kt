package com.michaeltchuang.walletsdk.runtimeaware.wallet.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.michaeltchuang.walletsdk.runtimeaware.wallet.typography.AlgoKitTypography

val LocalCustomColors = staticCompositionLocalOf {
    ThemedColors.defaultColor
}

val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

@Composable
fun AlgoKitTheme(
    content: @Composable () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    val customColors = ThemedColors.getColorsByMode(isDarkState.value)
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState,
        LocalCustomColors provides customColors
    ) {
        val isDark by isDarkState
        content()
    }
}

object AlgoKitTheme {
    val colors: AlgoKitColor
        @Composable
        get() = LocalCustomColors.current

    val typography
        @Composable
        get() = AlgoKitTypography()
}

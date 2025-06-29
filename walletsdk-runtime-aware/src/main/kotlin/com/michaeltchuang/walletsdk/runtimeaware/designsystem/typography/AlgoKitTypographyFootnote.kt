package com.michaeltchuang.walletsdk.runtimeaware.designsystem.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.peraMono
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.peraSans

@Composable
fun getPeraTypographyFootnote(): PeraTypography.Footnote = PeraTypography.Footnote(
    sans = TextStyle(
        fontFamily = peraSans,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    sansBold = TextStyle(
        fontFamily = peraSans,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.06).sp
    ),
    sansMedium = TextStyle(
        fontFamily = peraSans,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    mono = TextStyle(
        fontFamily = peraMono,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.72).sp
    ),
    monoMedium = TextStyle(
        fontFamily = peraMono,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.72).sp
    )
)

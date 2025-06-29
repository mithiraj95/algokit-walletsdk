package com.michaeltchuang.walletsdk.runtimeaware.designsystem.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun getPeraTypographyTitle(): PeraTypography.Title = PeraTypography.Title(
    regular = getTitleRegular(),
    large = getTitleLarge(),
    small = getTitleSmall()
)

@Composable
private fun getTitleRegular(): PeraTypography.Title.TitleRegular =
    PeraTypography.Title.TitleRegular(
        sans = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.36).sp
        ),
        sansMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.36).sp
        ),
        sansBold = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = (-0.36).sp
        )
    )

@Composable
private fun getTitleLarge(): PeraTypography.Title.TitleLarge =
    PeraTypography.Title.TitleLarge(
        sans = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            letterSpacing = (-0.36).sp
        ),
        sansMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            letterSpacing = (-0.36).sp
        ),
        mono = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            letterSpacing = (-0.72).sp
        ),
        monoMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            letterSpacing = (-0.72).sp
        )
    )

@Composable
private fun getTitleSmall(): PeraTypography.Title.TitleSmall =
    PeraTypography.Title.TitleSmall(
        sans = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = (-0.36).sp
        ),
        sansMedium = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = (-0.36).sp
        )
    )

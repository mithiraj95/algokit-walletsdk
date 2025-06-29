package com.michaeltchuang.walletsdk.runtimeaware.designsystem.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.peraMono
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.peraSans


@Composable
fun getPeraTypographyBody(): PeraTypography.Body = PeraTypography.Body(
    regular = getBodyRegular(),
    large = getBodyLarge()
)

@Composable
private fun getBodyRegular(): PeraTypography.Body.BodyRegular =
    PeraTypography.Body.BodyRegular(
        sans = TextStyle(
            fontFamily = peraSans,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        sansMedium = TextStyle(
            fontFamily = peraSans,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        sansBold = TextStyle(
            fontFamily = peraSans,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.sp
        ),
        mono = TextStyle(
            fontFamily = peraMono,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.72).sp
        ),
        monoMedium = TextStyle(
            fontFamily = peraMono,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.72).sp
        )
    )

@Composable
private fun getBodyLarge(): PeraTypography.Body.BodyLarge =
    PeraTypography.Body.BodyLarge(
        sans = TextStyle(
            fontFamily = peraSans,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        sansMedium = TextStyle(
            fontFamily = peraSans,
            fontWeight = FontWeight.Medium,
            fontSize = 19.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        mono = TextStyle(
            fontFamily = peraMono,
            fontWeight = FontWeight.Normal,
            fontSize = 19.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.72).sp
        )
    )

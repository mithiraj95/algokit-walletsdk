package com.michaeltchuang.wallet

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.utils.getSavedThemePreferenceFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    companion object {
        private const val MIN_SPLASH_DURATION_MS: Long = 1200L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlgoKitTheme {
                SplashLoading()
            }
        }

        val splashStartTimeMs = System.currentTimeMillis()
        lifecycleScope.launch {
            val pref = getSavedThemePreferenceFlow(this@SplashActivity).first()
            val mode = pref.convertToSystemAbbr()
            if (AppCompatDelegate.getDefaultNightMode() != mode) {
                AppCompatDelegate.setDefaultNightMode(mode)
            }

            val elapsedMs = System.currentTimeMillis() - splashStartTimeMs
            val remainingMs = MIN_SPLASH_DURATION_MS - elapsedMs
            if (remainingMs > 0) {
                delay(remainingMs)
            }

            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}

@Composable
private fun SplashLoading() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = AlgoKitTheme.colors.background),
        contentAlignment = Alignment.Center,
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.confetti_animation
            )
        )
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}

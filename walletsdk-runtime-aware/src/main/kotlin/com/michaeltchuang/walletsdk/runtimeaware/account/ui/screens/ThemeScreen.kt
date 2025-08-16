package com.michaeltchuang.walletsdk.runtimeaware.account.ui.screens

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.michaeltchuang.walletsdk.runtimeaware.R
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.theme.AlgoKitTheme
import com.michaeltchuang.walletsdk.runtimeaware.designsystem.widget.AlgoKitTopBar
import com.michaeltchuang.walletsdk.runtimeaware.utils.ThemeListItem
import com.michaeltchuang.walletsdk.runtimeaware.utils.ThemePreference
import com.michaeltchuang.walletsdk.runtimeaware.utils.getSavedThemePreferenceFlow
import com.michaeltchuang.walletsdk.runtimeaware.utils.saveThemePreference
import kotlinx.coroutines.launch

@Composable
fun ThemeScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val currentThemePreference by getSavedThemePreferenceFlow(context)
        .collectAsState(initial = null)

    LaunchedEffect(currentThemePreference) {
        val newPref = currentThemePreference
        val mode = newPref?.convertToSystemAbbr()
        if (AppCompatDelegate.getDefaultNightMode() != mode) {
            mode?.let { AppCompatDelegate.setDefaultNightMode(it) }
        }
    }

    val themeOptions: List<ThemeListItem> = remember(currentThemePreference) {
        ThemePreference.entries.map { pref ->
            pref.convertToThemeListItem(isSelected = pref == currentThemePreference)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AlgoKitTheme.colors.background)
            .padding(horizontal = 16.dp)
    ) {
        // Top App Bar
        AlgoKitTopBar(title = stringResource(R.string.theme)) {
            navController.popBackStack()
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Theme options
        themeOptions.forEach { theme ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        theme.convertToThemePreference()?.let { selectedPref ->
                            if (selectedPref != currentThemePreference) {
                                coroutineScope.launch {
                                    saveThemePreference(context, selectedPref)
                                }
                            }
                        }
                    }
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = stringResource(theme.themeResId),
                    color = AlgoKitTheme.colors.textMain,
                    modifier = Modifier.weight(1f),
                    style = AlgoKitTheme.typography.body.regular.sansMedium,
                )
                RadioButton(
                    selected = theme.isSelected,
                    onClick = {
                        theme.convertToThemePreference()?.let { selectedPref ->
                            if (selectedPref != currentThemePreference) {
                                coroutineScope.launch {
                                    saveThemePreference(context, selectedPref)
                                }
                            }
                        }
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = AlgoKitTheme.colors.positive,
                        unselectedColor = Color.LightGray
                    )
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ThemeScreenPreview() {
    AlgoKitTheme {
        ThemeScreen(rememberNavController())
    }
}

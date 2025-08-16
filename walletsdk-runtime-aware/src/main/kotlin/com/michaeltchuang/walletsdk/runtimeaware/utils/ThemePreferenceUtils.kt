package com.michaeltchuang.walletsdk.runtimeaware.utils

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.michaeltchuang.walletsdk.runtimeaware.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

private val Context.themeDataStore by preferencesDataStore(name = "theme_preferences")
private val THEME_PREFERENCE_KEY = stringPreferencesKey("theme_preference_key")

suspend fun saveThemePreference(context: Context, themePreference: ThemePreference) {
    context.themeDataStore.edit { prefs ->
        prefs[THEME_PREFERENCE_KEY] = themePreference.name
    }
}

fun getSavedThemePreferenceFlow(context: Context): Flow<ThemePreference> {
    return context.themeDataStore.data
        .map { prefs ->
            val savedKey = prefs[THEME_PREFERENCE_KEY]
            runCatching { ThemePreference.valueOf(savedKey ?: ThemePreference.SYSTEM_DEFAULT.name) }
                .getOrDefault(ThemePreference.SYSTEM_DEFAULT)
        }
        .distinctUntilChanged()
}

enum class ThemePreference(
     val visibleNameResId: Int
) {
    SYSTEM_DEFAULT(R.string.system_default),
    LIGHT(R.string.light),
    DARK(R.string.dark);

    fun convertToSystemAbbr(): Int {
        return when (this) {
            SYSTEM_DEFAULT -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            DARK -> AppCompatDelegate.MODE_NIGHT_YES
            LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
        }
    }

    fun convertToThemeListItem(isSelected: Boolean): ThemeListItem {
        return ThemeListItem(name, visibleNameResId, isSelected)
    }
}

data class ThemeListItem(
    val themeId: String,
    val themeResId: Int,
    var isSelected: Boolean
) {
    fun convertToThemePreference(): ThemePreference? {
        val themePreference = ThemePreference.values().firstOrNull { it.name == themeId }
        if (themePreference == null) {
            val exception = Exception("theme preference not found for theme id: $themeId")
        }
        return themePreference
    }
}

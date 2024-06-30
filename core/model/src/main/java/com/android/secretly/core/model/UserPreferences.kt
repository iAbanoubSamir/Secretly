package com.android.secretly.core.model

/**
 * Class that holds user preferences.
 */
data class UserPreferences(
    val isLoggedIn: Boolean,
    val shouldShowOnBoarding: Boolean,
    val darkMode: Boolean,
    val useDynamicColor: Boolean
)

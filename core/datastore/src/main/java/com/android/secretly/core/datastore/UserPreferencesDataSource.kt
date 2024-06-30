package com.android.secretly.core.datastore

import androidx.datastore.core.DataStore
import com.android.secretly.core.model.UserPreferences

/**
 * Data source for [UserPreferences]. Implementation for [DataStore].
 */
class UserPreferencesDataSource(
    private val userPreferences: DataStore<UserPreferences>
) {

    /**
     * Update user login status.
     * @param isLoggedIn Whether the user is logged in or not.
     */
    suspend fun setIsLoggedInPreference(isLoggedIn: Boolean) {
        userPreferences.updateData { currentPreferences ->
            currentPreferences.copy(isLoggedIn = isLoggedIn)
        }
    }

    /**
     * Update the onboarding screen status.
     * @param shouldShowOnBoarding Whether the onboarding screen should be shown or not.
     */
    suspend fun setShouldShowOnBoardingPreference(shouldShowOnBoarding: Boolean) {
        userPreferences.updateData { currentPreferences ->
            currentPreferences.copy(shouldShowOnBoarding = shouldShowOnBoarding)
        }
    }

    /**
     * Update dark mode status.
     * @param darkMode Whether the dark mode should be enabled or disabled.
     */
    suspend fun setDarkModePreference(darkMode: Boolean) {
        userPreferences.updateData { currentPreferences ->
            currentPreferences.copy(darkMode = darkMode)
        }
    }

    /**
     * Update dynamic color status.
     * @param useDynamicColor Whether the dynamic color should be enabled or disabled.
     */
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        userPreferences.updateData { currentPreferences ->
            currentPreferences.copy(useDynamicColor = useDynamicColor)
        }
    }
}
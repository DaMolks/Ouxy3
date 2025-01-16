package com.damolks.ouxy3.data.repository

import android.content.Context
import androidx.core.content.edit

class PreferencesRepository(context: Context) {
    
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    suspend fun isFirstLaunch(): Boolean {
        val isFirst = prefs.getBoolean(KEY_FIRST_LAUNCH, true)
        if (isFirst) {
            prefs.edit {
                putBoolean(KEY_FIRST_LAUNCH, false)
            }
        }
        return isFirst
    }

    companion object {
        private const val PREFS_NAME = "ouxy_prefs"
        private const val KEY_FIRST_LAUNCH = "first_launch"
    }
}
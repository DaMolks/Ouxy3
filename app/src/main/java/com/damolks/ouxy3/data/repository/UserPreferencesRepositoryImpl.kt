package com.damolks.ouxy3.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class UserPreferencesRepositoryImpl(
    private val context: Context
) : UserPreferencesRepository {

    companion object {
        private val Context.dataStore by preferencesDataStore("settings")
        private val SYNC_ENABLED = booleanPreferencesKey("sync_enabled")
        private val SYNC_FREQUENCY = intPreferencesKey("sync_frequency")
        private val OFFLINE_MODE = booleanPreferencesKey("offline_mode")
    }

    override suspend fun setSyncEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[SYNC_ENABLED] = enabled
        }
    }

    override suspend fun getSyncEnabled(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[SYNC_ENABLED] ?: true
    }

    override suspend fun setSyncFrequency(minutes: Int) {
        context.dataStore.edit { preferences ->
            preferences[SYNC_FREQUENCY] = minutes
        }
    }

    override suspend fun getSyncFrequency(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[SYNC_FREQUENCY] ?: 60
    }

    override suspend fun setOfflineMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[OFFLINE_MODE] = enabled
        }
    }

    override suspend fun getOfflineMode(): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences[OFFLINE_MODE] ?: false
    }
}

package com.damolks.ouxy3.data.repository

interface UserPreferencesRepository {
    suspend fun setSyncEnabled(enabled: Boolean)
    suspend fun getSyncEnabled(): Boolean
    suspend fun setSyncFrequency(minutes: Int)
    suspend fun getSyncFrequency(): Int
    suspend fun setOfflineMode(enabled: Boolean)
    suspend fun getOfflineMode(): Boolean
}

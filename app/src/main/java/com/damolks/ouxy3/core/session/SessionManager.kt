package com.damolks.ouxy3.core.session

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SessionManager(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "ouxy_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun isOnboardingCompleted(): Boolean {
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETED, false)
    }

    fun setOnboardingCompleted() {
        prefs.edit {
            putBoolean(KEY_ONBOARDING_COMPLETED, true)
        }
    }

    fun getTechnicianId(): Long? {
        val id = prefs.getLong(KEY_TECHNICIAN_ID, -1L)
        return if (id != -1L) id else null
    }

    fun setTechnicianId(id: Long) {
        prefs.edit {
            putLong(KEY_TECHNICIAN_ID, id)
        }
    }

    fun clearSession() {
        prefs.edit {
            clear()
        }
    }

    companion object {
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
        private const val KEY_TECHNICIAN_ID = "technician_id"
    }
}
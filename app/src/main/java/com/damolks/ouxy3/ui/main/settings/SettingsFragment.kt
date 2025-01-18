package com.damolks.ouxy3.ui.main.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.damolks.ouxy3.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        setupPreferenceCallbacks()
    }

    private fun setupPreferenceCallbacks() {
        findPreference<Preference>("edit_profile")?.setOnPreferenceClickListener {
            // TODO: Navigation vers l'édition du profil
            true
        }

        findPreference<Preference>("edit_signature")?.setOnPreferenceClickListener {
            // TODO: Navigation vers l'édition de la signature
            true
        }

        findPreference<Preference>("sync_now")?.setOnPreferenceClickListener {
            // TODO: Déclencher la synchronisation
            true
        }

        findPreference<Preference>("clear_cache")?.setOnPreferenceClickListener {
            // TODO: Nettoyer le cache
            true
        }
    }
}
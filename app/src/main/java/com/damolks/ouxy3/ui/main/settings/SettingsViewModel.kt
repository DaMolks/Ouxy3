package com.damolks.ouxy3.ui.main.settings

import androidx.lifecycle.ViewModel
import com.damolks.ouxy3.data.repository.UserPreferencesRepository

class SettingsViewModel(
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel()
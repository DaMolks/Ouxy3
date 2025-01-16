package com.damolks.ouxy3.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.repository.PreferencesRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _navigationEvent = MutableLiveData<NavigationDestination>()
    val navigationEvent: LiveData<NavigationDestination> = _navigationEvent

    fun checkFirstLaunch() {
        viewModelScope.launch {
            val isFirstLaunch = preferencesRepository.isFirstLaunch()
            _navigationEvent.value = if (isFirstLaunch) {
                NavigationDestination.ONBOARDING
            } else {
                NavigationDestination.MAIN
            }
        }
    }

    enum class NavigationDestination {
        ONBOARDING,
        MAIN
    }
}
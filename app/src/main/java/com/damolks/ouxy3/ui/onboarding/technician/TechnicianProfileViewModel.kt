package com.damolks.ouxy3.ui.onboarding.technician

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.repository.TechnicianRepository
import kotlinx.coroutines.launch

class TechnicianProfileViewModel(
    private val repository: TechnicianRepository
) : ViewModel() {

    private val _state = MutableLiveData<TechnicianProfileState>(TechnicianProfileState.Initial)
    val state: LiveData<TechnicianProfileState> = _state

    private val _events = MutableLiveData<TechnicianProfileEvent?>()
    val events: LiveData<TechnicianProfileEvent?> = _events

    fun saveTechnician(formState: TechnicianFormState) {
        viewModelScope.launch {
            try {
                _state.value = TechnicianProfileState.Loading
                repository.saveTechnician(formState.toTechnician())
                _events.value = TechnicianProfileEvent.NavigateToSignature
            } catch (e: Exception) {
                _state.value = TechnicianProfileState.Error(e.message ?: "Une erreur est survenue")
            }
        }
    }

    fun clearEvent() {
        _events.value = null
    }
}
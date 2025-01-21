package com.damolks.ouxy3.ui.onboarding.technician

import android.util.Log
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
                Log.d("TechnicianProfileVM", "Saving technician: ${formState}")
                val technician = formState.toTechnician()
                Log.d("TechnicianProfileVM", "Mapped to technician: ${technician}")
                repository.saveTechnician(technician)
                Log.d("TechnicianProfileVM", "Successfully saved technician")
                _events.value = TechnicianProfileEvent.NavigateToSignature
            } catch (e: Exception) {
                Log.e("TechnicianProfileVM", "Error saving technician", e)
                _state.value = TechnicianProfileState.Error(e.message ?: "Une erreur est survenue")
            }
        }
    }

    fun clearEvent() {
        _events.value = null
    }
}
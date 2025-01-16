package com.damolks.ouxy3.ui.onboarding.technician

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.model.Technician
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
        if (!validateForm(formState)) {
            _state.value = TechnicianProfileState.Error("Tous les champs sont obligatoires")
            return
        }

        viewModelScope.launch {
            try {
                _state.value = TechnicianProfileState.Loading

                val technician = Technician(
                    firstName = formState.firstName,
                    lastName = formState.lastName,
                    sector = formState.sector,
                    matricule = formState.matricule,
                    teamLeader = formState.teamLeader
                )

                repository.insertTechnician(technician)
                _events.value = TechnicianProfileEvent.NavigateToSignature
            } catch (e: Exception) {
                _state.value = TechnicianProfileState.Error(e.message ?: "Une erreur est survenue")
            }
        }
    }

    private fun validateForm(state: TechnicianFormState): Boolean {
        return state.firstName.isNotBlank() &&
                state.lastName.isNotBlank() &&
                state.sector.isNotBlank() &&
                state.matricule.isNotBlank() &&
                state.teamLeader.isNotBlank()
    }

    fun clearEvent() {
        _events.value = null
    }
}

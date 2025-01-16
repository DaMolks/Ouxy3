package com.damolks.ouxy3.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.R
import com.damolks.ouxy3.data.model.Technician
import com.damolks.ouxy3.data.repository.TechnicianRepository
import com.damolks.ouxy3.util.Event
import kotlinx.coroutines.launch

class TechnicianProfileViewModel(
    private val technicianRepository: TechnicianRepository
) : ViewModel() {

    private val _formState = MutableLiveData(TechnicianFormState())
    val formState: LiveData<TechnicianFormState> = _formState

    private val _navigationEvent = MutableLiveData<Event<Unit>>()
    val navigationEvent: LiveData<Event<Unit>> = _navigationEvent

    fun updateFirstName(firstName: String) {
        _formState.value = _formState.value?.copy(
            firstName = firstName,
            firstNameError = validateFirstName(firstName)
        )
    }

    fun updateLastName(lastName: String) {
        _formState.value = _formState.value?.copy(
            lastName = lastName,
            lastNameError = validateLastName(lastName)
        )
    }

    fun updateSector(sector: String) {
        _formState.value = _formState.value?.copy(
            sector = sector,
            sectorError = validateSector(sector)
        )
    }

    fun updateMatricule(matricule: String) {
        _formState.value = _formState.value?.copy(
            matricule = matricule,
            matriculeError = validateMatricule(matricule)
        )
    }

    fun updateTeamLeader(teamLeader: String) {
        _formState.value = _formState.value?.copy(
            teamLeader = teamLeader,
            teamLeaderError = validateTeamLeader(teamLeader)
        )
    }

    fun saveAndContinue() {
        val currentState = _formState.value ?: return
        if (!currentState.isValid) return

        viewModelScope.launch {
            val technician = Technician(
                firstName = currentState.firstName,
                lastName = currentState.lastName,
                sector = currentState.sector,
                matricule = currentState.matricule,
                teamLeader = currentState.teamLeader,
                signature = null
            )
            technicianRepository.saveTechnicianProfile(technician)
            _navigationEvent.value = Event(Unit)
        }
    }

    private fun validateFirstName(firstName: String): Int? {
        return if (firstName.isBlank()) R.string.error_field_required else null
    }

    private fun validateLastName(lastName: String): Int? {
        return if (lastName.isBlank()) R.string.error_field_required else null
    }

    private fun validateSector(sector: String): Int? {
        return if (sector.isBlank()) R.string.error_field_required else null
    }

    private fun validateMatricule(matricule: String): Int? {
        return if (matricule.isBlank()) R.string.error_field_required else null
    }

    private fun validateTeamLeader(teamLeader: String): Int? {
        return if (teamLeader.isBlank()) R.string.error_field_required else null
    }

    data class TechnicianFormState(
        val firstName: String = "",
        val firstNameError: Int? = null,
        val lastName: String = "",
        val lastNameError: Int? = null,
        val sector: String = "",
        val sectorError: Int? = null,
        val matricule: String = "",
        val matriculeError: Int? = null,
        val teamLeader: String = "",
        val teamLeaderError: Int? = null
    ) {
        val isValid: Boolean
            get() = firstNameError == null && firstName.isNotBlank() &&
                    lastNameError == null && lastName.isNotBlank() &&
                    sectorError == null && sector.isNotBlank() &&
                    matriculeError == null && matricule.isNotBlank() &&
                    teamLeaderError == null && teamLeader.isNotBlank()
    }
}
package com.damolks.ouxy3.ui.onboarding.technician

import com.damolks.ouxy3.data.repository.Technician

sealed class TechnicianProfileState {
    object Initial : TechnicianProfileState()
    object Loading : TechnicianProfileState()
    data class Error(val message: String) : TechnicianProfileState()
}

data class TechnicianFormState(
    val firstName: String = "",
    val lastName: String = "",
    val sector: String = "",
    val matricule: String = "",
    val teamLeader: String = ""
)

fun TechnicianFormState.toTechnician() = Technician(
    firstName = firstName,
    lastName = lastName,
    sector = sector,
    matricule = matricule,
    teamLeader = teamLeader
)

sealed class TechnicianProfileEvent {
    object NavigateToSignature : TechnicianProfileEvent()
}
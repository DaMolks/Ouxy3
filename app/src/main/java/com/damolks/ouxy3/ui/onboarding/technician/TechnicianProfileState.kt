package com.damolks.ouxy3.ui.onboarding.technician

import com.damolks.ouxy3.data.model.Technician

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
    id = 0L,  // Auto-generated
    firstName = firstName,
    lastName = lastName,
    sector = sector,
    matricule = matricule,
    teamLeader = teamLeader,
    signaturePath = null,
    createdAt = System.currentTimeMillis(),
    updatedAt = System.currentTimeMillis()
)

sealed class TechnicianProfileEvent {
    object NavigateToSignature : TechnicianProfileEvent()
}
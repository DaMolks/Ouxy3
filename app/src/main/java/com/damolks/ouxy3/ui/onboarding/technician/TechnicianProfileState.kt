package com.damolks.ouxy3.ui.onboarding.technician

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

sealed class TechnicianProfileEvent {
    object NavigateToSignature : TechnicianProfileEvent()
}

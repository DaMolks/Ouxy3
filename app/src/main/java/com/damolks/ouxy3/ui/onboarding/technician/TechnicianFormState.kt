package com.damolks.ouxy3.ui.onboarding.technician

import com.damolks.ouxy3.data.repository.Technician

data class TechnicianFormState(
    val firstName: String,
    val lastName: String,
    val matricule: String,
    val sector: String,
    val teamLeader: String
)

fun TechnicianFormState.toTechnician() = Technician(
    firstName = firstName,
    lastName = lastName,
    matricule = matricule,
    sector = sector,
    teamLeader = teamLeader
)
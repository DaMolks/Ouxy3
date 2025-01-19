package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.ui.onboarding.technician.TechnicianFormState

interface TechnicianRepository {
    suspend fun saveTechnician(technician: Technician)
}

data class Technician(
    val firstName: String,
    val lastName: String,
    val matricule: String,
    val sector: String,
    val teamLeader: String
)
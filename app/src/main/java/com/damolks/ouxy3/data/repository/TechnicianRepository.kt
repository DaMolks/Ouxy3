package com.damolks.ouxy3.data.repository

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

fun TechnicianFormState.toTechnician() = Technician(
    firstName = firstName,
    lastName = lastName,
    matricule = matricule,
    sector = sector,
    teamLeader = teamLeader
)
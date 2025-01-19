package com.damolks.ouxy3.data.repository

import kotlinx.coroutines.flow.Flow

interface TechnicianRepository {
    suspend fun saveTechnician(technician: Technician)
    fun getAllTechnicians(): Flow<List<Technician>>
}

data class Technician(
    val firstName: String,
    val lastName: String,
    val matricule: String,
    val sector: String,
    val teamLeader: String
)
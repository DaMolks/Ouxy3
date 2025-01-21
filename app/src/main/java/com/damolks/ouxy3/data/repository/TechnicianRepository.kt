package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.model.Technician
import kotlinx.coroutines.flow.Flow

interface TechnicianRepository {
    suspend fun saveTechnician(technician: Technician)
    fun getAllTechnicians(): Flow<List<Technician>>
}
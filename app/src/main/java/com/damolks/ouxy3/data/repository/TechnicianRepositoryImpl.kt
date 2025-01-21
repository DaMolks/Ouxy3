package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.local.TechnicianDao
import com.damolks.ouxy3.data.model.Technician
import kotlinx.coroutines.flow.Flow

class TechnicianRepositoryImpl(
    private val technicianDao: TechnicianDao
) : TechnicianRepository {

    override suspend fun saveTechnician(technician: Technician) {
        technicianDao.insert(technician)
    }

    override fun getAllTechnicians(): Flow<List<Technician>> {
        return technicianDao.getAllTechnicians()
    }
}
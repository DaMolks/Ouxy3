package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.db.TechnicianDao
import com.damolks.ouxy3.data.model.toDomain
import com.damolks.ouxy3.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TechnicianRepositoryImpl(
    private val technicianDao: TechnicianDao
) : TechnicianRepository {

    override suspend fun saveTechnician(technician: Technician) {
        technicianDao.insertTechnician(technician.toEntity())
    }

    override fun getAllTechnicians(): Flow<List<Technician>> {
        return technicianDao.getAllTechnicians().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
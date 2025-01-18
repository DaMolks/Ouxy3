package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.db.TechnicianDao

class TechnicianRepositoryImpl(
    private val technicianDao: TechnicianDao
) : TechnicianRepository {

    override suspend fun saveTechnician(technician: Technician) {
        technicianDao.insertTechnician(technician.toEntity())
    }
}

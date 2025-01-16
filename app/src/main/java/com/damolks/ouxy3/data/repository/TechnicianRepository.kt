package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.database.dao.TechnicianDao
import com.damolks.ouxy3.data.model.Technician
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class TechnicianRepository(private val technicianDao: TechnicianDao) {

    fun getAllTechnicians(): Flow<List<Technician>> = technicianDao.getAllTechnicians()

    suspend fun getTechnicianById(id: Long): Technician? = technicianDao.getTechnicianById(id)

    suspend fun getTechnicianByMatricule(matricule: String): Technician? =
        technicianDao.getTechnicianByMatricule(matricule)

    suspend fun insertTechnician(technician: Technician): Long =
        technicianDao.insertTechnician(technician)

    suspend fun updateTechnician(technician: Technician) {
        technicianDao.updateTechnician(
            technician.copy(updatedAt = LocalDateTime.now())
        )
    }

    suspend fun updateTechnicianSignature(technicianId: Long, signaturePath: String) {
        getTechnicianById(technicianId)?.let { technician ->
            updateTechnician(
                technician.copy(
                    signaturePath = signaturePath,
                    updatedAt = LocalDateTime.now()
                )
            )
        }
    }

    suspend fun deleteTechnician(technician: Technician) =
        technicianDao.deleteTechnician(technician)
}
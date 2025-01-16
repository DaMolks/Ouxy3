package com.damolks.ouxy3.data.database.dao

import androidx.room.*
import com.damolks.ouxy3.data.model.Technician
import kotlinx.coroutines.flow.Flow

@Dao
interface TechnicianDao {
    @Query("SELECT * FROM technicians")
    fun getAllTechnicians(): Flow<List<Technician>>

    @Query("SELECT * FROM technicians WHERE id = :id")
    suspend fun getTechnicianById(id: Long): Technician?

    @Insert
    suspend fun insertTechnician(technician: Technician): Long

    @Update
    suspend fun updateTechnician(technician: Technician)

    @Delete
    suspend fun deleteTechnician(technician: Technician)

    @Query("SELECT * FROM technicians WHERE matricule = :matricule")
    suspend fun getTechnicianByMatricule(matricule: String): Technician?
}
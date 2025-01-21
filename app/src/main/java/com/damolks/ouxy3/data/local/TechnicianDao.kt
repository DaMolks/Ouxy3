package com.damolks.ouxy3.data.local

import androidx.room.*
import com.damolks.ouxy3.data.model.Technician
import kotlinx.coroutines.flow.Flow

@Dao
interface TechnicianDao {
    @Query("SELECT * FROM technicians")
    fun getAllTechnicians(): Flow<List<Technician>>

    @Query("SELECT * FROM technicians WHERE id = :id")
    suspend fun getTechnicianById(id: Long): Technician?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(technician: Technician): Long

    @Update
    suspend fun update(technician: Technician)

    @Delete
    suspend fun delete(technician: Technician)

    @Query("UPDATE technicians SET signaturePath = :path WHERE id = :id")
    suspend fun updateSignature(id: Long, path: String)
}
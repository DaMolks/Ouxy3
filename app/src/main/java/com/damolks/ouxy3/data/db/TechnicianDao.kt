package com.damolks.ouxy3.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.damolks.ouxy3.data.model.TechnicianEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TechnicianDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTechnician(technician: TechnicianEntity)

    @Query("SELECT * FROM technicians")
    fun getAllTechnicians(): Flow<List<TechnicianEntity>>
}
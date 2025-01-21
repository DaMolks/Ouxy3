package com.damolks.ouxy3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "technicians")
data class Technician(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val sector: String,
    val matricule: String,
    val teamLeader: String,
    val signaturePath: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
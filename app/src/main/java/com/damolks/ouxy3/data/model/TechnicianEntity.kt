package com.damolks.ouxy3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.damolks.ouxy3.data.repository.Technician

@Entity(tableName = "technicians")
data class TechnicianEntity(
    @PrimaryKey
    val matricule: String,
    val firstName: String,
    val lastName: String,
    val sector: String,
    val teamLeader: String
)

fun Technician.toEntity() = TechnicianEntity(
    matricule = matricule,
    firstName = firstName,
    lastName = lastName,
    sector = sector,
    teamLeader = teamLeader
)

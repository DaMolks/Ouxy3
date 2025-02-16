package com.damolks.ouxy3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "technicians")
data class TechnicianEntity(
    @PrimaryKey
    val matricule: String,
    val firstName: String,
    val lastName: String,
    val sector: String,
    val teamLeader: String
)

fun TechnicianEntity.toDomain() = Technician(
    matricule = matricule,
    firstName = firstName,
    lastName = lastName,
    sector = sector,
    teamLeader = teamLeader
)

fun Technician.toEntity() = TechnicianEntity(
    matricule = matricule,
    firstName = firstName,
    lastName = lastName,
    sector = sector,
    teamLeader = teamLeader
)
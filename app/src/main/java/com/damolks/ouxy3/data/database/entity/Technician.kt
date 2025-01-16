package com.damolks.ouxy3.data.database.entity

import android.graphics.Bitmap
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
    val signature: Bitmap?
)
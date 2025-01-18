package com.damolks.ouxy3.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marketplace_modules")
data class MarketplaceModule(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val category: String,
    val version: String,
    val isInstalled: Boolean = false,
    val requiresPermissions: List<String> = emptyList(),
    val size: Long = 0L
)
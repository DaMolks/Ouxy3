package com.damolks.ouxy3.data.db

import androidx.room.*
import com.damolks.ouxy3.data.model.MarketplaceModule

@Dao
interface MarketplaceDao {
    @Query("SELECT * FROM marketplace_modules ORDER BY name ASC")
    suspend fun getAllModules(): List<MarketplaceModule>

    @Query("SELECT * FROM marketplace_modules WHERE category = :category ORDER BY name ASC")
    suspend fun getModulesByCategory(category: String): List<MarketplaceModule>

    @Query("UPDATE marketplace_modules SET isInstalled = :isInstalled WHERE id = :moduleId")
    suspend fun updateModuleInstallStatus(moduleId: String, isInstalled: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModule(module: MarketplaceModule)

    @Delete
    suspend fun deleteModule(module: MarketplaceModule)
}
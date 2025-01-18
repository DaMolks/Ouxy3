package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.db.MarketplaceDao
import com.damolks.ouxy3.data.model.MarketplaceModule

class MarketplaceRepository(private val marketplaceDao: MarketplaceDao) {

    suspend fun getModules(category: String = "Tous"): List<MarketplaceModule> {
        return if (category == "Tous") {
            marketplaceDao.getAllModules()
        } else {
            marketplaceDao.getModulesByCategory(category)
        }
    }

    suspend fun installModule(moduleId: String) {
        // TODO: Implémenter l'installation réelle du module
        marketplaceDao.updateModuleInstallStatus(moduleId, true)
    }

    suspend fun uninstallModule(moduleId: String) {
        // TODO: Implémenter la désinstallation réelle du module
        marketplaceDao.updateModuleInstallStatus(moduleId, false)
    }
}
package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.database.dao.SiteDao
import com.damolks.ouxy3.data.model.Site
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class SiteRepository(private val siteDao: SiteDao) {

    fun getAllSites(): Flow<List<Site>> = siteDao.getAllSites()

    suspend fun getSiteById(id: Long): Site? = siteDao.getSiteById(id)

    suspend fun insertSite(site: Site): Long = siteDao.insertSite(site)

    suspend fun updateSite(site: Site) {
        siteDao.updateSite(
            site.copy(updatedAt = LocalDateTime.now())
        )
    }

    suspend fun deleteSite(site: Site) = siteDao.deleteSite(site)
}
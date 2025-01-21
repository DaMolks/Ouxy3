package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.local.SiteDao
import kotlinx.coroutines.flow.Flow

class SiteRepositoryImpl(
    private val siteDao: SiteDao
) : SiteRepository {
    override fun getAllSites(): Flow<List<Site>> = siteDao.getAllSites()
    
    override suspend fun getSiteById(id: Long): Site? = siteDao.getSiteById(id)
    
    override suspend fun insertSite(site: Site): Long = siteDao.insert(site)
    
    override suspend fun updateSite(site: Site) = siteDao.update(site)
    
    override suspend fun deleteSite(site: Site) = siteDao.delete(site)
}
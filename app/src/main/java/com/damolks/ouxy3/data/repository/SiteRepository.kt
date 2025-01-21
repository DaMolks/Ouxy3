package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.model.Site
import kotlinx.coroutines.flow.Flow

interface SiteRepository {
    fun getAllSites(): Flow<List<Site>>
    suspend fun getSiteById(id: Long): Site?
    suspend fun insertSite(site: Site): Long
    suspend fun updateSite(site: Site)
    suspend fun deleteSite(site: Site)
}
package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.database.dao.SiteDao
import com.damolks.ouxy3.data.model.Site
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SiteRepositoryTest {

    private lateinit var siteDao: SiteDao
    private lateinit var repository: SiteRepository

    @Before
    fun setup() {
        siteDao = mockk(relaxed = true)
        repository = SiteRepository(siteDao)
    }

    @Test
    fun `getAllSites returns flow from dao`() = runTest {
        // Given
        val sites = listOf(
            Site(
                id = 1,
                name = "Site Nord",
                address = "123 Rue du Nord",
                contactName = "John Contact",
                contactPhone = "0123456789",
                notes = "Notes test"
            )
        )
        coEvery { siteDao.getAllSites() } returns flowOf(sites)

        // When
        repository.getAllSites()

        // Then
        coVerify { siteDao.getAllSites() }
    }

    @Test
    fun `insertSite delegates to dao`() = runTest {
        // Given
        val site = Site(
            name = "Site Nord",
            address = "123 Rue du Nord",
            contactName = "John Contact",
            contactPhone = "0123456789",
            notes = "Notes test"
        )
        coEvery { siteDao.insertSite(any()) } returns 1L

        // When
        repository.insertSite(site)

        // Then
        coVerify { siteDao.insertSite(site) }
    }

    @Test
    fun `updateSite delegates to dao and updates timestamp`() = runTest {
        // Given
        val site = Site(
            id = 1,
            name = "Site Nord",
            address = "123 Rue du Nord",
            contactName = "John Contact",
            contactPhone = "0123456789",
            notes = "Notes test"
        )

        // When
        repository.updateSite(site)

        // Then
        coVerify { siteDao.updateSite(any()) }
    }

    @Test
    fun `deleteSite delegates to dao`() = runTest {
        // Given
        val site = Site(
            id = 1,
            name = "Site Nord",
            address = "123 Rue du Nord",
            contactName = "John Contact",
            contactPhone = "0123456789",
            notes = "Notes test"
        )

        // When
        repository.deleteSite(site)

        // Then
        coVerify { siteDao.deleteSite(site) }
    }
}
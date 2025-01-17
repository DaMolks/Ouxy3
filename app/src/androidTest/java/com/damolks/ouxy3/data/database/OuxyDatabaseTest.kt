package com.damolks.ouxy3.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.damolks.ouxy3.data.database.dao.SiteDao
import com.damolks.ouxy3.data.database.dao.TechnicianDao
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.model.Technician
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class OuxyDatabaseTest {
    private lateinit var technicianDao: TechnicianDao
    private lateinit var siteDao: SiteDao
    private lateinit var db: OuxyDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, OuxyDatabase::class.java
        ).build()
        technicianDao = db.technicianDao()
        siteDao = db.siteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadTechnician() = runBlocking {
        val technician = Technician(
            firstName = "John",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        
        val id = technicianDao.insertTechnician(technician)
        val loaded = technicianDao.getTechnicianById(id)

        assertEquals(technician.firstName, loaded?.firstName)
        assertEquals(technician.lastName, loaded?.lastName)
        assertEquals(technician.sector, loaded?.sector)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndDeleteTechnician() = runBlocking {
        val technician = Technician(
            firstName = "John",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        
        val id = technicianDao.insertTechnician(technician)
        val loaded = technicianDao.getTechnicianById(id)
        technicianDao.deleteTechnician(loaded!!)

        val deleted = technicianDao.getTechnicianById(id)
        assertNull(deleted)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndReadSite() = runBlocking {
        val site = Site(
            name = "Test Site",
            address = "123 Test Street",
            contactName = "John Contact",
            contactPhone = "0123456789",
            notes = "Test notes",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        
        val id = siteDao.insertSite(site)
        val loaded = siteDao.getSiteById(id)

        assertEquals(site.name, loaded?.name)
        assertEquals(site.address, loaded?.address)
        assertEquals(site.contactName, loaded?.contactName)
    }

    @Test
    @Throws(Exception::class)
    fun readAllSites() = runBlocking {
        val sites = listOf(
            Site(
                name = "Site 1",
                address = "Address 1",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            ),
            Site(
                name = "Site 2",
                address = "Address 2",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )

        sites.forEach { siteDao.insertSite(it) }
        val loaded = siteDao.getAllSites().first()

        assertEquals(2, loaded.size)
        assertEquals(sites[0].name, loaded[0].name)
        assertEquals(sites[1].name, loaded[1].name)
    }
}
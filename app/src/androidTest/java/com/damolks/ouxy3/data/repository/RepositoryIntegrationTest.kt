package com.damolks.ouxy3.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.damolks.ouxy3.data.database.OuxyDatabase
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.model.Technician
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class RepositoryIntegrationTest {
    private lateinit var db: OuxyDatabase
    private lateinit var technicianRepository: TechnicianRepository
    private lateinit var siteRepository: SiteRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, OuxyDatabase::class.java
        ).build()

        technicianRepository = TechnicianRepository(db.technicianDao())
        siteRepository = SiteRepository(db.siteDao())
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
        db.close()
    }

    @Test
    fun technicianRepositoryIntegrationTest() = runBlocking {
        // Créer et insérer un technicien
        val technician = Technician(
            firstName = "John",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val id = technicianRepository.insertTechnician(technician)
        assertNotNull(id)

        // Récupérer et vérifier le technicien
        val loaded = technicianRepository.getTechnicianById(id)
        assertNotNull(loaded)
        assertEquals(technician.firstName, loaded?.firstName)
        assertEquals(technician.lastName, loaded?.lastName)

        // Mettre à jour la signature
        val signaturePath = "/path/to/signature.png"
        technicianRepository.updateTechnicianSignature(id, signaturePath)

        // Vérifier la mise à jour
        val updated = technicianRepository.getTechnicianById(id)
        assertEquals(signaturePath, updated?.signaturePath)
    }

    @Test
    fun siteRepositoryIntegrationTest() = runBlocking {
        // Créer et insérer plusieurs sites
        val sites = listOf(
            Site(
                name = "Site 1",
                address = "123 Test St",
                contactName = "Contact 1",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            ),
            Site(
                name = "Site 2",
                address = "456 Test Ave",
                contactName = "Contact 2",
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )

        val ids = sites.map { siteRepository.insertSite(it) }

        // Vérifier que tous les sites sont récupérés
        val loadedSites = siteRepository.getAllSites().first()
        assertEquals(sites.size, loadedSites.size)

        // Mettre à jour un site
        val siteToUpdate = loadedSites.first()
        val updatedSite = siteToUpdate.copy(name = "Updated Site")
        siteRepository.updateSite(updatedSite)

        // Vérifier la mise à jour
        val reloadedSite = siteRepository.getSiteById(siteToUpdate.id)
        assertEquals("Updated Site", reloadedSite?.name)

        // Supprimer un site
        siteRepository.deleteSite(updatedSite)

        // Vérifier la suppression
        val remainingSites = siteRepository.getAllSites().first()
        assertEquals(sites.size - 1, remainingSites.size)
    }
}
package com.damolks.ouxy3.data.repository

import com.damolks.ouxy3.data.database.dao.TechnicianDao
import com.damolks.ouxy3.data.model.Technician
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class TechnicianRepositoryTest {

    private lateinit var technicianDao: TechnicianDao
    private lateinit var repository: TechnicianRepository

    @Before
    fun setup() {
        technicianDao = mockk(relaxed = true)
        repository = TechnicianRepository(technicianDao)
    }

    @Test
    fun `getAllTechnicians returns flow from dao`() = runTest {
        // Given
        val technicians = listOf(
            Technician(
                id = 1,
                firstName = "John",
                lastName = "Doe",
                sector = "Nord",
                matricule = "T123",
                teamLeader = "Jane Smith"
            )
        )
        coEvery { technicianDao.getAllTechnicians() } returns flowOf(technicians)

        // When
        repository.getAllTechnicians()

        // Then
        coVerify { technicianDao.getAllTechnicians() }
    }

    @Test
    fun `insertTechnician delegates to dao`() = runTest {
        // Given
        val technician = Technician(
            firstName = "John",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith"
        )
        coEvery { technicianDao.insertTechnician(any()) } returns 1L

        // When
        repository.insertTechnician(technician)

        // Then
        coVerify { technicianDao.insertTechnician(technician) }
    }

    @Test
    fun `updateTechnicianSignature updates technician with new signature path`() = runTest {
        // Given
        val technicianId = 1L
        val signaturePath = "path/to/signature.png"
        val technician = Technician(
            id = technicianId,
            firstName = "John",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith"
        )

        coEvery { technicianDao.getTechnicianById(technicianId) } returns technician
        
        // When
        repository.updateTechnicianSignature(technicianId, signaturePath)

        // Then
        coVerify { 
            technicianDao.getTechnicianById(technicianId)
            technicianDao.updateTechnician(any())
        }
    }
}
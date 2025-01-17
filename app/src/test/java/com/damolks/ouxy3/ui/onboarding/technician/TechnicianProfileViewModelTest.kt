package com.damolks.ouxy3.ui.onboarding.technician

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.damolks.ouxy3.data.repository.TechnicianRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TechnicianProfileViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: TechnicianRepository
    private lateinit var viewModel: TechnicianProfileViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        viewModel = TechnicianProfileViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `saveTechnician with valid form saves technician and navigates`() = runTest {
        // Given
        val formState = TechnicianFormState(
            firstName = "John",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith"
        )
        coEvery { repository.insertTechnician(any()) } returns 1L

        // When
        viewModel.saveTechnician(formState)

        // Then
        coVerify { repository.insertTechnician(any()) }
        assertEquals(TechnicianProfileEvent.NavigateToSignature, viewModel.events.value)
    }

    @Test
    fun `saveTechnician with invalid form shows error`() = runTest {
        // Given
        val formState = TechnicianFormState(
            firstName = "",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith"
        )

        // When
        viewModel.saveTechnician(formState)

        // Then
        assert(viewModel.state.value is TechnicianProfileState.Error)
        coVerify(exactly = 0) { repository.insertTechnician(any()) }
    }

    @Test
    fun `clearEvent sets events to null`() {
        // Given
        viewModel.saveTechnician(TechnicianFormState(
            firstName = "John",
            lastName = "Doe",
            sector = "Nord",
            matricule = "T123",
            teamLeader = "Jane Smith"
        ))

        // When
        viewModel.clearEvent()

        // Then
        assertEquals(null, viewModel.events.value)
    }
}
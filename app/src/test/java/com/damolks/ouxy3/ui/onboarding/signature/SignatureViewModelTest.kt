package com.damolks.ouxy3.ui.onboarding.signature

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.damolks.ouxy3.data.repository.TechnicianRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
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
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
class SignatureViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: TechnicianRepository
    private lateinit var viewModel: SignatureViewModel
    private lateinit var mockBitmap: Bitmap
    private lateinit var mockFile: File

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        mockBitmap = mockk(relaxed = true)
        mockFile = mockk(relaxed = true) {
            every { absolutePath } returns "/test/path"
        }
        viewModel = SignatureViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `saveSignature updates technician and navigates`() = runTest {
        // Given
        val technicianId = 1L
        val filesDir = mockk<File> {
            every { mkdirs() } returns true
            every { absolutePath } returns "/test/path"
        }
        coEvery { repository.updateTechnicianSignature(any(), any()) } returns Unit

        // When
        viewModel.saveSignature(mockBitmap, technicianId, filesDir)

        // Then
        coVerify { repository.updateTechnicianSignature(technicianId, any()) }
        assertEquals(SignatureEvent.NavigateToSites, viewModel.events.value)
        assert(viewModel.state.value !is SignatureState.Error)
    }

    @Test
    fun `saveSignature handles error`() = runTest {
        // Given
        val technicianId = 1L
        val filesDir = mockk<File> {
            every { mkdirs() } returns false
        }

        // When
        viewModel.saveSignature(mockBitmap, technicianId, filesDir)

        // Then
        assert(viewModel.state.value is SignatureState.Error)
        coVerify(exactly = 0) { repository.updateTechnicianSignature(any(), any()) }
    }

    @Test
    fun `clearEvent sets events to null`() {
        // Given
        val technicianId = 1L
        val filesDir = mockk<File>(relaxed = true)
        viewModel.saveSignature(mockBitmap, technicianId, filesDir)

        // When
        viewModel.clearEvent()

        // Then
        assertEquals(null, viewModel.events.value)
    }
}
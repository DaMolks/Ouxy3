package com.damolks.ouxy3.ui.onboarding.sites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.repository.SiteRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class SitesSetupViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var repository: SiteRepository
    private lateinit var viewModel: SitesSetupViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        viewModel = SitesSetupViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init loads sites`() = runTest {
        // Given
        val sites = listOf(
            Site(
                id = 1,
                name = "Test Site",
                address = "123 Test St",
                contactName = "John Doe",
                contactPhone = null,
                notes = null
            )
        )
        coEvery { repository.getAllSites() } returns flowOf(sites)

        // When
        val viewModel = SitesSetupViewModel(repository)

        // Then
        assertEquals(sites, viewModel.sites.value)
    }

    @Test
    fun `addSite successful shows success state`() = runTest {
        // Given
        coEvery { repository.insertSite(any()) } returns 1L

        // When
        viewModel.addSite("Test Site", "123 Test St", "John Doe")

        // Then
        assert(viewModel.state.value is SitesSetupState.SiteAdded)
        coVerify { repository.insertSite(any()) }
    }

    @Test
    fun `addSite failure shows error state`() = runTest {
        // Given
        coEvery { repository.insertSite(any()) } throws Exception("Test error")

        // When
        viewModel.addSite("Test Site", "123 Test St", "John Doe")

        // Then
        assert(viewModel.state.value is SitesSetupState.Error)
    }

    @Test
    fun `deleteSite delegates to repository`() = runTest {
        // Given
        val site = Site(
            id = 1,
            name = "Test Site",
            address = "123 Test St",
            contactName = "John Doe",
            contactPhone = null,
            notes = null
        )

        // When
        viewModel.deleteSite(site)

        // Then
        coVerify { repository.deleteSite(site) }
    }

    @Test
    fun `finishSetup triggers navigation event`() {
        // When
        viewModel.finishSetup()

        // Then
        assertEquals(SitesSetupEvent.NavigateToMain, viewModel.events.value)
    }

    @Test
    fun `clearEvent sets events to null`() {
        // Given
        viewModel.finishSetup()

        // When
        viewModel.clearEvent()

        // Then
        assertEquals(null, viewModel.events.value)
    }
}
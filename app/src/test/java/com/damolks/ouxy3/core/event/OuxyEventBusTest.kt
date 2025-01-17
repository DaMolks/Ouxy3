package com.damolks.ouxy3.core.event

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OuxyEventBusTest {

    @Test
    fun `published event is received by subscriber`() = runTest {
        val event = TechnicianProfileUpdatedEvent(
            technicianId = 1L,
            source = "test"
        )

        // Collect events in a separate coroutine
        val collectJob = launch(UnconfinedTestDispatcher()) {
            OuxyEventBus.subscribe("TECHNICIAN_PROFILE_UPDATED").collect { receivedEvent ->
                assertEquals(event, receivedEvent)
            }
        }

        // Publish event
        OuxyEventBus.publish(event)

        // Cleanup
        collectJob.cancel()
    }

    @Test
    fun `subscriber only receives events of subscribed type`() = runTest {
        val techEvent = TechnicianProfileUpdatedEvent(
            technicianId = 1L,
            source = "test"
        )
        val siteEvent = SiteUpdatedEvent(
            siteId = 1L,
            source = "test"
        )

        var receivedEvents = 0
        val collectJob = launch(UnconfinedTestDispatcher()) {
            OuxyEventBus.subscribe("TECHNICIAN_PROFILE_UPDATED").collect {
                receivedEvents++
                assertEquals(techEvent, it)
            }
        }

        // Publish both events
        OuxyEventBus.publish(techEvent)
        OuxyEventBus.publish(siteEvent)

        // Only one event should have been received
        assertEquals(1, receivedEvents)

        // Cleanup
        collectJob.cancel()
    }

    @Test
    fun `subscribeAll receives all events`() = runTest {
        val techEvent = TechnicianProfileUpdatedEvent(
            technicianId = 1L,
            source = "test"
        )
        val siteEvent = SiteUpdatedEvent(
            siteId = 1L,
            source = "test"
        )

        var receivedEvents = 0
        val collectJob = launch(UnconfinedTestDispatcher()) {
            OuxyEventBus.subscribeAll().collect {
                receivedEvents++
            }
        }

        // Publish both events
        OuxyEventBus.publish(techEvent)
        OuxyEventBus.publish(siteEvent)

        // Both events should have been received
        assertEquals(2, receivedEvents)

        // Cleanup
        collectJob.cancel()
    }
}
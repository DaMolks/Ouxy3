package com.damolks.ouxy3.core.event

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filter

/**
 * EventBus principal pour la communication inter-modules
 */
object OuxyEventBus {
    private val _events = MutableSharedFlow<OuxyEvent>()
    private val events = _events.asSharedFlow()

    /**
     * Publie un événement dans le bus
     */
    suspend fun publish(event: OuxyEvent) {
        _events.emit(event)
    }

    /**
     * S'abonne aux événements d'un type spécifique
     */
    fun subscribe(eventType: String): Flow<OuxyEvent> {
        return events.filter { it.type == eventType }
    }

    /**
     * S'abonne à tous les événements
     */
    fun subscribeAll(): Flow<OuxyEvent> {
        return events
    }
}
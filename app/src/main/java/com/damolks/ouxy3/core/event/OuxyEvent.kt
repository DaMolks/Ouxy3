package com.damolks.ouxy3.core.event

/**
 * Interface de base pour tous les événements du système
 */
interface OuxyEvent {
    /**
     * Type de l'événement pour le routage et la validation
     */
    val type: String

    /**
     * Version de l'événement pour la compatibilité
     */
    val version: Int

    /**
     * Source de l'événement (module émetteur)
     */
    val source: String

    /**
     * Timestamp de création de l'événement
     */
    val timestamp: Long
}
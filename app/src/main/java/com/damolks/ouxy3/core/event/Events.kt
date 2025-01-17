package com.damolks.ouxy3.core.event

/**
 * Événement de base avec implémentation des champs communs
 */
abstract class BaseEvent : OuxyEvent {
    override val timestamp: Long = System.currentTimeMillis()
}

/**
 * Événement de mise à jour du profil technicien
 */
data class TechnicianProfileUpdatedEvent(
    val technicianId: Long,
    override val source: String
) : BaseEvent() {
    override val type: String = "TECHNICIAN_PROFILE_UPDATED"
    override val version: Int = 1
}

/**
 * Événement de mise à jour d'un site
 */
data class SiteUpdatedEvent(
    val siteId: Long,
    override val source: String
) : BaseEvent() {
    override val type: String = "SITE_UPDATED"
    override val version: Int = 1
}

/**
 * Événement de changement de configuration
 */
data class ConfigurationChangedEvent(
    val configType: String,
    override val source: String
) : BaseEvent() {
    override val type: String = "CONFIGURATION_CHANGED"
    override val version: Int = 1
}
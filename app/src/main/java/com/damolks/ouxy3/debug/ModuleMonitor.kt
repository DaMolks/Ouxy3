package com.damolks.ouxy3.debug

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.Instant

class ModuleMonitor(
    private val maxStoredErrors: Int = 50 // Limite le nombre d'erreurs stockées
) {
    private val _moduleStates = MutableStateFlow<Map<String, ModuleState>>(emptyMap())
    val moduleStates: Flow<Map<String, ModuleState>> = _moduleStates

    private val _errors = MutableStateFlow<List<ModuleError>>(emptyList())
    val errors: Flow<List<ModuleError>> = _errors

    fun updateModuleState(moduleId: String, state: ModuleState) {
        val current = _moduleStates.value.toMutableMap()
        current[moduleId] = state
        _moduleStates.value = current
    }

    fun reportError(moduleId: String, error: Throwable, severity: ErrorSeverity = ErrorSeverity.ERROR) {
        val currentErrors = _errors.value.toMutableList()
        
        // Ajoute la nouvelle erreur
        currentErrors.add(
            ModuleError(
                moduleId = moduleId,
                error = error,
                timestamp = Instant.now(),
                severity = severity
            )
        )

        // Trie par timestamp décroissant et limite la taille
        _errors.value = currentErrors
            .sortedByDescending { it.timestamp }
            .take(maxStoredErrors)

        // Met à jour l'état du module avec la dernière erreur
        updateModuleState(
            moduleId,
            _moduleStates.value[moduleId]?.copy(
                lastError = error.message,
                lastErrorTimestamp = Instant.now()
            ) ?: ModuleState(
                isLoaded = false,
                isActive = false,
                lastError = error.message,
                lastErrorTimestamp = Instant.now()
            )
        )
    }

    fun clearErrors(moduleId: String? = null) {
        if (moduleId == null) {
            _errors.value = emptyList()
        } else {
            _errors.value = _errors.value.filter { it.moduleId != moduleId }
        }
    }

    fun getErrorsByModule(moduleId: String): List<ModuleError> =
        _errors.value.filter { it.moduleId == moduleId }

    fun getErrorsBySeverity(severity: ErrorSeverity): List<ModuleError> =
        _errors.value.filter { it.severity == severity }
}

data class ModuleState(
    val isLoaded: Boolean,
    val isActive: Boolean,
    val lastError: String?,
    val lastErrorTimestamp: Instant? = null
)

data class ModuleError(
    val moduleId: String,
    val error: Throwable,
    val timestamp: Instant,
    val severity: ErrorSeverity
)

enum class ErrorSeverity {
    DEBUG, INFO, WARNING, ERROR, CRITICAL
}
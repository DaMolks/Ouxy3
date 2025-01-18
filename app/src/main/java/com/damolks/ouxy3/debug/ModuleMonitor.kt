package com.damolks.ouxy3.debug

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ModuleMonitor {
    private val _moduleStates = MutableStateFlow<Map<String, ModuleState>>(emptyMap())
    val moduleStates: Flow<Map<String, ModuleState>> = _moduleStates

    private val _errors = MutableLiveData<List<ModuleError>>()
    val errors: LiveData<List<ModuleError>> = _errors

    fun updateModuleState(moduleId: String, state: ModuleState) {
        val current = _moduleStates.value.toMutableMap()
        current[moduleId] = state
        _moduleStates.value = current
    }

    fun reportError(moduleId: String, error: Throwable) {
        val currentErrors = _errors.value?.toMutableList() ?: mutableListOf()
        currentErrors.add(ModuleError(moduleId, error))
        _errors.value = currentErrors
    }

    fun clearErrors(moduleId: String) {
        _errors.value = _errors.value?.filter { it.moduleId != moduleId }
    }
}

data class ModuleState(
    val isLoaded: Boolean,
    val isActive: Boolean,
    val lastError: String?
)

data class ModuleError(
    val moduleId: String,
    val error: Throwable
)
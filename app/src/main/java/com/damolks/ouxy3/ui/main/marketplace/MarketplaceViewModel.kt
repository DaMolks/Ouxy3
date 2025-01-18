package com.damolks.ouxy3.ui.main.marketplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.model.MarketplaceModule
import com.damolks.ouxy3.data.repository.MarketplaceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarketplaceViewModel(
    private val repository: MarketplaceRepository
) : ViewModel() {

    private val _modules = MutableStateFlow<List<MarketplaceModule>>(emptyList())
    val modules: StateFlow<List<MarketplaceModule>> = _modules

    val categories = listOf(
        "Tous", "Rapports", "Photos", "Signature", "Export", "Synchronisation"
    )

    init {
        loadModules()
    }

    private fun loadModules(category: String = "Tous") {
        viewModelScope.launch {
            val modules = repository.getModules(category)
            _modules.value = modules
        }
    }

    fun selectCategory(category: String) {
        loadModules(category)
    }

    fun installModule(moduleId: String) {
        viewModelScope.launch {
            repository.installModule(moduleId)
            loadModules() // Refresh list
        }
    }
}
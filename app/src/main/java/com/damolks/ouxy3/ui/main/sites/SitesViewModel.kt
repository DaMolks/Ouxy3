package com.damolks.ouxy3.ui.main.sites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.repository.SiteRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SitesViewModel(
    private val siteRepository: SiteRepository
) : ViewModel() {

    val sites = siteRepository.getAllSites()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _events = MutableSharedFlow<SiteEvent>()
    val events: SharedFlow<SiteEvent> = _events

    fun addSite(site: Site) {
        viewModelScope.launch {
            try {
                siteRepository.insertSite(site)
                _events.emit(SiteEvent.Success("Site ajouté avec succès"))
            } catch (e: Exception) {
                _events.emit(SiteEvent.Error("Erreur lors de l'ajout du site"))
            }
        }
    }

    fun updateSite(site: Site) {
        viewModelScope.launch {
            try {
                siteRepository.updateSite(site)
                _events.emit(SiteEvent.Success("Site mis à jour avec succès"))
            } catch (e: Exception) {
                _events.emit(SiteEvent.Error("Erreur lors de la mise à jour du site"))
            }
        }
    }

    fun deleteSite(site: Site) {
        viewModelScope.launch {
            try {
                siteRepository.deleteSite(site)
                _events.emit(SiteEvent.Success("Site supprimé avec succès"))
            } catch (e: Exception) {
                _events.emit(SiteEvent.Error("Erreur lors de la suppression du site"))
            }
        }
    }
}
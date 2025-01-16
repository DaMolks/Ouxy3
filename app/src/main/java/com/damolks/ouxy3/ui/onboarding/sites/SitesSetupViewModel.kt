package com.damolks.ouxy3.ui.onboarding.sites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.model.Site
import com.damolks.ouxy3.data.repository.SiteRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SitesSetupViewModel(
    private val siteRepository: SiteRepository
) : ViewModel() {

    private val _state = MutableLiveData<SitesSetupState>(SitesSetupState.Initial)
    val state: LiveData<SitesSetupState> = _state

    private val _sites = MutableLiveData<List<Site>>()
    val sites: LiveData<List<Site>> = _sites

    private val _events = MutableLiveData<SitesSetupEvent?>()
    val events: LiveData<SitesSetupEvent?> = _events

    init {
        loadSites()
    }

    private fun loadSites() {
        siteRepository.getAllSites()
            .onEach { sites ->
                _sites.value = sites
            }
            .catch { e ->
                _state.value = SitesSetupState.Error(e.message ?: "Une erreur est survenue")
            }
            .launchIn(viewModelScope)
    }

    fun addSite(name: String, address: String, clientName: String?) {
        viewModelScope.launch {
            try {
                _state.value = SitesSetupState.Loading

                val site = Site(
                    name = name,
                    address = address,
                    contactName = clientName,
                    contactPhone = null,
                    notes = null
                )

                siteRepository.insertSite(site)
                _state.value = SitesSetupState.SiteAdded
            } catch (e: Exception) {
                _state.value = SitesSetupState.Error(e.message ?: "Une erreur est survenue")
            }
        }
    }

    fun deleteSite(site: Site) {
        viewModelScope.launch {
            try {
                siteRepository.deleteSite(site)
            } catch (e: Exception) {
                _state.value = SitesSetupState.Error(e.message ?: "Une erreur est survenue")
            }
        }
    }

    fun finishSetup() {
        _events.value = SitesSetupEvent.NavigateToMain
    }

    fun clearEvent() {
        _events.value = null
    }
}

sealed class SitesSetupState {
    object Initial : SitesSetupState()
    object Loading : SitesSetupState()
    object SiteAdded : SitesSetupState()
    data class Error(val message: String) : SitesSetupState()
}

sealed class SitesSetupEvent {
    object NavigateToMain : SitesSetupEvent()
}
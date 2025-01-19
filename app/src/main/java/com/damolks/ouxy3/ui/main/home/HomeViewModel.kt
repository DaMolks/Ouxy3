package com.damolks.ouxy3.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.repository.SiteRepository
import com.damolks.ouxy3.data.repository.TechnicianRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val technicianRepository: TechnicianRepository,
    private val siteRepository: SiteRepository
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>(HomeState.Loading)
    val state: LiveData<HomeState> = _state

    init {
        loadData()
    }

    fun refresh() {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = HomeState.Loading

            technicianRepository.getAllTechnicians()
                .combine(siteRepository.getAllSites()) { technicians, sites ->
                    val technician = technicians.firstOrNull()
                    technician?.let { tech ->
                        HomeState.Content(
                            technicianName = "${tech.firstName} ${tech.lastName}",
                            siteCount = sites.size,
                            sector = tech.sector,
                            teamLeader = tech.teamLeader
                        )
                    } ?: HomeState.Error("Aucun technicien trouvé")
                }
                .catch { e ->
                    _state.value = HomeState.Error(e.message ?: "Une erreur est survenue")
                }
                .onEach { state ->
                    _state.value = state
                }
                .launchIn(viewModelScope)
        }
    }
}

sealed class HomeState {
    object Loading : HomeState()
    data class Content(
        val technicianName: String,
        val siteCount: Int,
        val sector: String,
        val teamLeader: String
    ) : HomeState()
    data class Error(val message: String) : HomeState()
}
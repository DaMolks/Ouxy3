package com.damolks.ouxy3.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.repository.TechnicianRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class ProfileViewModel(
    private val technicianRepository: TechnicianRepository
) : ViewModel() {

    private val _state = MutableLiveData<ProfileState>(ProfileState.Loading)
    val state: LiveData<ProfileState> = _state

    init {
        loadProfile()
    }

    private fun loadProfile() {
        technicianRepository.getAllTechnicians()
            .map { technicians ->
                val technician = technicians.firstOrNull()
                technician?.let {
                    ProfileState.Content(
                        technicianName = "${it.firstName} ${it.lastName}",
                        matricule = it.matricule,
                        sector = it.sector,
                        teamLeader = it.teamLeader
                    )
                } ?: ProfileState.Error("Aucun technicien trouvé")
            }
            .catch { e ->
                _state.value = ProfileState.Error(e.message ?: "Une erreur est survenue")
            }
            .onEach { state ->
                _state.value = state
            }
            .launchIn(viewModelScope)
    }
}

sealed class ProfileState {
    object Loading : ProfileState()
    data class Error(val message: String) : ProfileState()
    data class Content(
        val technicianName: String,
        val matricule: String,
        val sector: String,
        val teamLeader: String
    ) : ProfileState()
}
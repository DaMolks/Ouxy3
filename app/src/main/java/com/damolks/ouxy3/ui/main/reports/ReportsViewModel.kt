package com.damolks.ouxy3.ui.main.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReportsViewModel : ViewModel() {

    private val _state = MutableLiveData<ReportsState>(ReportsState.Loading)
    val state: LiveData<ReportsState> = _state

    init {
        // Pour l'instant, affichage de l'état vide
        _state.value = ReportsState.Empty
    }
}

sealed class ReportsState {
    object Loading : ReportsState()
    object Empty : ReportsState()
    data class Content(val reports: List<Report>) : ReportsState()
}

data class Report(
    val id: Long,
    val title: String,
    val date: String,
    val status: ReportStatus
)

enum class ReportStatus {
    DRAFT,
    SUBMITTED,
    APPROVED,
    REJECTED
}
package com.damolks.ouxy3.ui.onboarding.signature

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.repository.TechnicianRepository
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class SignatureViewModel(
    private val technicianRepository: TechnicianRepository
) : ViewModel() {

    private val _state = MutableLiveData<SignatureState>(SignatureState.Initial)
    val state: LiveData<SignatureState> = _state

    private val _events = MutableLiveData<SignatureEvent?>()
    val events: LiveData<SignatureEvent?> = _events

    fun saveSignature(bitmap: Bitmap, filesDir: File) {
        viewModelScope.launch {
            try {
                _state.value = SignatureState.Loading

                // Créer le dossier signatures s'il n'existe pas
                val signatureDir = File(filesDir, "signatures").apply { mkdirs() }
                val signatureFile = File(signatureDir, "signature_temp.png")

                // Sauvegarder le bitmap
                FileOutputStream(signatureFile).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
                }

                _events.value = SignatureEvent.NavigateToSites
            } catch (e: Exception) {
                _state.value = SignatureState.Error(e.message ?: "Une erreur est survenue")
            }
        }
    }

    fun clearEvent() {
        _events.value = null
    }
}

sealed class SignatureState {
    object Initial : SignatureState()
    object Loading : SignatureState()
    data class Error(val message: String) : SignatureState()
}

sealed class SignatureEvent {
    object NavigateToSites : SignatureEvent()
}
package com.damolks.ouxy3.ui.onboarding

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.damolks.ouxy3.data.repository.TechnicianRepository
import com.damolks.ouxy3.util.Event
import kotlinx.coroutines.launch

class SignatureViewModel(
    private val technicianRepository: TechnicianRepository
) : ViewModel() {

    private val _navigationEvent = MutableLiveData<Event<Unit>>()
    val navigationEvent: LiveData<Event<Unit>> = _navigationEvent

    fun saveSignature(signature: Bitmap) {
        viewModelScope.launch {
            technicianRepository.saveSignature(signature)
            _navigationEvent.value = Event(Unit)
        }
    }
}
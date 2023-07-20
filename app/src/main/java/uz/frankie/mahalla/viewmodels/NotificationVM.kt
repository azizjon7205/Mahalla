package uz.frankie.mahalla.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.frankie.mahalla.model.FCMNote
import uz.frankie.mahalla.model.FCMResp
import uz.frankie.mahalla.repositories.NotificationRepository
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.UiStateObject
import uz.frankie.mahalla.viewmodels.state.NotificationState
import javax.inject.Inject

@HiltViewModel
class NotificationVM @Inject constructor(private val notificationRepository: NotificationRepository): ViewModel() {

    private var notificationJob: Job? = null

    private val _uiState = MutableStateFlow(NotificationState())
    val uiState = _uiState.asStateFlow()

    fun sendMessage(message: FCMNote){
        notificationJob?.cancel()
        notificationJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when(val result = notificationRepository.sendMessage(message)){
                is NetworkResource.Success -> {
                    _uiState.update { it.copy(notification = result.data!!, isLoading = false) }
                }
                is NetworkResource.Error -> {
                    _uiState.update { it.copy(errorMessage = result.uiText.toString(), isLoading = false) }
                }
            }
        }
    }
}
package uz.frankie.mahalla.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import uz.frankie.mahalla.viewmodels.state.NetworkState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val sharedPreferenceHelper: SharedPreferenceHelper): ViewModel() {
    private var splashJob: Job? = null

    private var _uiState = MutableStateFlow(NetworkState<String>())
    val uiState = _uiState.asStateFlow()

    fun navigateToNext(){
        splashJob?.cancel()
        splashJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            _uiState.update { it.copy(data = sharedPreferenceHelper.getAccessToken(), isLoading = false) }

        }
    }
}
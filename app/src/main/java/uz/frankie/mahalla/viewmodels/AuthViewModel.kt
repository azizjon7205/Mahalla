package uz.frankie.mahalla.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.frankie.mahalla.model.LoginRequest
import uz.frankie.mahalla.model.LoginResponse
import uz.frankie.mahalla.repositories.AuthRepository
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.SharedPreferenceHelper
import uz.frankie.mahalla.viewmodels.state.NetworkState
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository, private val sharedPreferenceHelper: SharedPreferenceHelper): ViewModel() {

    private var authJob: Job? = null

    private val _uiState = MutableStateFlow(NetworkState<LoginResponse>())
    val uiState = _uiState.asStateFlow()

    fun login(loginRequest: LoginRequest){
        authJob?.cancel()
        authJob = viewModelScope.launch{
            _uiState.update { it.copy(isLoading = true) }
            when(val result = authRepository.login(loginRequest)){
                is NetworkResource.Success -> {
                    _uiState.update { it.copy(data = result.data!!, isLoading = false) }
                    sharedPreferenceHelper.setAccessToken(result.data!!.access)
                    sharedPreferenceHelper.setRole(result.data.role)
                }
                is NetworkResource.Error -> {
                    _uiState.update { it.copy(errorMessage = result.uiText.toString(), isLoading = false) }
                }
            }
        }
    }
}
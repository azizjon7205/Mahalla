package uz.frankie.mahalla.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.frankie.mahalla.repositories.NeighborhoodRepository
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.viewmodels.state.NeighborhoodState
import javax.inject.Inject

@HiltViewModel
class NeighborhoodVM @Inject constructor(
    private val repository: NeighborhoodRepository
) : ViewModel() {

    private var neighborhoodJob: Job? = null

    private val _uiState = MutableStateFlow(NeighborhoodState())
    val uiState = _uiState.asStateFlow()

    fun getNeighborhoodList(){
        neighborhoodJob?.cancel()
        neighborhoodJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when(val result = repository.getNeighborhoodList()){
                is NetworkResource.Success ->{
                    _uiState.update { it.copy(neighborhoodList = result.data!!, isLoading = false) }
                }
                is NetworkResource.Error ->{
                    _uiState.update { it.copy(errorMessage = result.uiText.toString(), isLoading = false) }
                }
            }
        }
    }

}
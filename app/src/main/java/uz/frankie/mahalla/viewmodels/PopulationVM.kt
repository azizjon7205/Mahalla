package uz.frankie.mahalla.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.frankie.mahalla.repositories.PopulationRepository
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.viewmodels.state.population.PopulationUiState
import javax.inject.Inject

@HiltViewModel
class PopulationVM @Inject constructor(
    private val repository: PopulationRepository
) : ViewModel() {

    private var populationJob: Job? = null


    private val _uiState = MutableStateFlow(PopulationUiState())
    val uiState = _uiState.asStateFlow()


    fun getPopulationList() {
        viewModelScope.launch {
            populationJob?.cancelAndJoin()
            populationJob = launch {
                _uiState.update { it.copy(isLoading = true) }
                when (val result = repository.getPopulationList()) {
                    is NetworkResource.Success -> {
                        _uiState.update {
                            it.copy(
                                populationList = result.data?.result ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                    is NetworkResource.Error -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = result.uiText,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }

    fun clearErrorMessage(){
        _uiState.update { it.copy(errorMessage = null) }
    }

}
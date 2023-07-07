package uz.frankie.mahalla.viewmodels.state.population

import uz.frankie.mahalla.network.models.population.response.PopulationDataModel
import uz.frankie.mahalla.utils.UiText

data class PopulationUiState(
    val populationList: List<PopulationDataModel> = emptyList(),
    val populationCount: Long = 0,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)

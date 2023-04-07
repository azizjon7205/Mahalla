package uz.frankie.mahalla.viewmodels.state

import uz.frankie.mahalla.network.models.population.response.NeighborhoodResponse

data class NeighborhoodState(
    val neighborhoodList: List<NeighborhoodResponse> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

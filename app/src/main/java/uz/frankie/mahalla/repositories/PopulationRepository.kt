package uz.frankie.mahalla.repositories

import retrofit2.HttpException
import uz.frankie.mahalla.network.models.population.response.PopulationResponse
import uz.frankie.mahalla.network.services.NeighborhoodService
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.UiText
import java.io.IOException
import javax.inject.Inject

class PopulationRepository @Inject constructor(
    private val service: NeighborhoodService
) {

    suspend fun getPopulationList(): NetworkResource<PopulationResponse> {
        return try {
            val response = service.getPopulationList()
            if (response.isSuccessful) {
                NetworkResource.Success(response.body()?.data)
            } else {
                NetworkResource.Error(
                    if (response.body()?.error != null) UiText.ErrorMessage(
                        response.body()?.error ?: ""
                    ) else UiText.StaticString()
                )
            }
        } catch (e: HttpException) {
            NetworkResource.Error(UiText.StaticString())
        } catch (e: IOException) {
            NetworkResource.Error(UiText.StaticString())
        }
    }

}
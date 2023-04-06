package uz.frankie.mahalla.repositories

import retrofit2.HttpException
import uz.frankie.mahalla.network.models.population.response.NeighborhoodResponse
import uz.frankie.mahalla.network.services.NeighborhoodService
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.UiText
import java.io.IOException
import javax.inject.Inject

class NeighborhoodRepository @Inject constructor(
    private val service: NeighborhoodService
) {

    suspend fun getNeighborhoodList(): NetworkResource<List<NeighborhoodResponse>> {
        return try {
            val response = service.getNeighborhoodList()
            if (response.isSuccessful) {
                NetworkResource.Success(response.body())
            } else {
                NetworkResource.Error(UiText.StaticString())
            }
        } catch (e: HttpException) {
            NetworkResource.Error(UiText.StaticString())
        } catch (e: IOException) {
            NetworkResource.Error(UiText.StaticString())
        }
    }

}

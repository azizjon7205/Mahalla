package uz.frankie.mahalla.repositories

import android.util.Log
import retrofit2.HttpException
import uz.frankie.mahalla.model.Neighborhood
import uz.frankie.mahalla.network.services.NeighborhoodService
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.UiText
import java.io.IOException
import javax.inject.Inject

class NeighborhoodRepository @Inject constructor(
    private val service: NeighborhoodService
) {

    suspend fun getNeighborhoodList(): NetworkResource<List<Neighborhood>> {
        return try {
            val response = service.getNeighborhoods()
            if (response.isSuccessful) {
                Log.d("@@@", "Villages list rep: ${response.body()?.data?.result}")
                val neighborhoods = response.body()?.data?.result?.neighborhoods
                neighborhoods?.forEach { neighborhood ->
                    neighborhood.workers.forEach {
                        if (it.role == "rais"){
                            neighborhood.fcm_token = it.fcm_token
                        }

                    }
                }
                NetworkResource.Success(neighborhoods)

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

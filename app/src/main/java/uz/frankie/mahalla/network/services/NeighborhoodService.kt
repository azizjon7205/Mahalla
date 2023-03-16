package uz.frankie.mahalla.network.services

import retrofit2.Response
import retrofit2.http.GET
import uz.frankie.mahalla.network.models.population.response.NeighborhoodResponse

interface NeighborhoodService {

    @GET("neighborhood")
    suspend fun getNeighborhoodList() : Response<List<NeighborhoodResponse>>

}
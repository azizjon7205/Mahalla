package uz.frankie.mahalla.network.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.frankie.mahalla.model.BaseResponse
import uz.frankie.mahalla.model.FCMNote
import uz.frankie.mahalla.model.FCMResponse
import uz.frankie.mahalla.model.LoginRequest
import uz.frankie.mahalla.model.LoginResponse
import uz.frankie.mahalla.model.NeighborhoodRes
import uz.frankie.mahalla.network.models.population.response.PopulationResponse

interface NeighborhoodService {

    @POST("/api/v1/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Response<BaseResponse<LoginResponse>>

    @GET("/api/v1/neighborhoods")
    suspend fun getNeighborhoods(): Response<BaseResponse<NeighborhoodRes>>

    @GET("/api/v1/populition")
    suspend fun getPopulationList(): Response<BaseResponse<PopulationResponse>>

    @POST("/api/v1/notification/")
    suspend fun sendNotification(@Body fcmNote: FCMNote): Response<FCMResponse>
}
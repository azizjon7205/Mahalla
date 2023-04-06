package uz.frankie.mahalla.network.services

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.frankie.mahalla.model.BaseResponse
import uz.frankie.mahalla.model.LoginRequest
import uz.frankie.mahalla.model.LoginResponse

interface GovernorService {

    @POST("/api/v1/login-for-worker")
    suspend fun login(@Body loginRequest: LoginRequest): Response<BaseResponse<LoginResponse>>
}
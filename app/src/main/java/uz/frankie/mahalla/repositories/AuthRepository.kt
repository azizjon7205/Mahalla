package uz.frankie.mahalla.repositories

import retrofit2.HttpException
import uz.frankie.mahalla.model.LoginRequest
import uz.frankie.mahalla.model.LoginResponse
import uz.frankie.mahalla.network.services.GovernorService
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.UiText
import java.io.IOException
import javax.inject.Inject

class AuthRepository @Inject constructor(private val governorService: GovernorService) {

    suspend fun login(loginRequest: LoginRequest): NetworkResource<LoginResponse>{
        return try {
            val response = governorService.login(loginRequest)
            if (response.isSuccessful){
                NetworkResource.Success(response.body()?.data)
            }else{
                NetworkResource.Error(UiText.StaticString())
            }
        }catch (e: HttpException){
            NetworkResource.Error(UiText.StaticString())
        }catch (e: IOException){
            NetworkResource.Error(UiText.StaticString())
        }
    }
}
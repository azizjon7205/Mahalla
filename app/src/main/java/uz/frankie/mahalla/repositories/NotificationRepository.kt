package uz.frankie.mahalla.repositories

import retrofit2.HttpException
import uz.frankie.mahalla.model.FCMNote
import uz.frankie.mahalla.model.FCMResp
import uz.frankie.mahalla.network.services.NotificationService
import uz.frankie.mahalla.utils.NetworkResource
import uz.frankie.mahalla.utils.UiText
import java.io.IOException
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val notificationService: NotificationService
) {
    suspend fun sendMessage(message: FCMNote): NetworkResource<FCMResp>{
        return try {
            val response = notificationService.sendNotification(message)
            if (response.isSuccessful){
                NetworkResource.Success(response.body())
            } else{
                NetworkResource.Error(UiText.StaticString())
            }
        } catch (e: HttpException){
            NetworkResource.Error(UiText.StaticString())
        } catch (e: IOException){
            NetworkResource.Error(UiText.StaticString())
        }
    }
}
package uz.frankie.mahalla.network.services

import uz.frankie.mahalla.model.FCMResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url
import uz.frankie.mahalla.model.FCMNote

interface NotificationService {

    companion object{
        private const val key = "AAAAVmO6KdA:APA91bH-oXaYj2uyp2DdOXZo6Lzs3OAup51uW8lt9IHFgoTpR3tAyqM-FMFd0L9p9eJ2-YkCwuEbovhiel9kWjc3BrOnNboSsnETns7NPzRn40DOjoKYlBmQwSFDNKS8Ny5IvwtbKKDK"
    }


    @Headers("Authorization: key=$key")
    @POST("/fcm/send")
    suspend fun sendNotification(@Body fcmNote: FCMNote): Response<FCMResp>
}
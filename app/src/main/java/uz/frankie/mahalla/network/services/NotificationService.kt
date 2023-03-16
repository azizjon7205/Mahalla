package uz.frankie.mahalla.network.services

import com.clone.instagramclone.model.FCMResp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import uz.frankie.mahalla.model.FCMNote

interface NotificationService {

    companion object{
        private const val key = "AAAAh59qCiU:APA91bErCgG4d6XzI6N9wzCUw96Tdk9RTUdSmYqpUCDZGOdpN" +
                "nN9HX-F-b8M81rhfrIlpDZwcWcbrS4ZQbfExeV3aVLaThDNdGvtCGs8I0W7bIcejsIT0n_kqSKmiqKR86EKooonLHRH"
    }

    @Headers("Authorization: key=$key")
    @POST("/fcm/send")
    suspend fun sendNotification(@Body fcmNote: FCMNote): Response<FCMResp>
}
package uz.frankie.mahalla.utils.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import uz.frankie.mahalla.utils.logger.Logger

class FCMService: FirebaseMessagingService() {
    val TAG = FCMService::class.java.simpleName
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Logger.i(TAG, "Refresh token :: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Logger.i(TAG, "Message :: $message")
    }
}
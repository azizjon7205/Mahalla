package uz.frankie.mahalla.viewmodels.state

import uz.frankie.mahalla.model.FCMResp
import uz.frankie.mahalla.model.FCMResponse

data class NotificationState(
    val notification: FCMResponse = FCMResponse(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
package uz.frankie.mahalla.viewmodels.state

import uz.frankie.mahalla.model.FCMResp

data class NotificationState(
    val notification: FCMResp? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
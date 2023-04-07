package uz.frankie.mahalla.viewmodels.state

import uz.frankie.mahalla.model.FCMResp

data class NetworkState<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
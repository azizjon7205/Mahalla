package uz.frankie.mahalla.viewmodels.state

import uz.frankie.mahalla.model.FCMResp

data class NetworkStateList<T>(
    val data: List<T> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
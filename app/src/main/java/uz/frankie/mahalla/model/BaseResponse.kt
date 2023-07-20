package uz.frankie.mahalla.model

data class BaseResponse<T>(val error: String, val data: T)

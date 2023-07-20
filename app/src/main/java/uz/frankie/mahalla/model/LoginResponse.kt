package uz.frankie.mahalla.model

data class LoginResponse(
    val status: Int,
    val id: String,
    val access: String,
    val refresh: String,
    val role: String,
    val message: String
)

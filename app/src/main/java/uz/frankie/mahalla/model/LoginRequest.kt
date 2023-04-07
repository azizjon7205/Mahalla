package uz.frankie.mahalla.model

data class LoginRequest(val phone_number: String, val password: String, val fcm_token: String) {
}
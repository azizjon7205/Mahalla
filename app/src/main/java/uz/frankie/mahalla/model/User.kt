package uz.frankie.mahalla.model

data class User(val id: Int, val role: String? = null, val name: String? = null, val token: String? = null) {
}
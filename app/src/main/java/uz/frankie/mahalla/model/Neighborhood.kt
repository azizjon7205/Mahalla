package uz.frankie.mahalla.model

data class Neighborhood(val id: String, val name: String, val address: String, val number: Int, val workers: List<Worker>, var fcm_token: String = "")

data class Worker(val id: String, val full_name: String, val role: String, val fcm_token: List<String>)

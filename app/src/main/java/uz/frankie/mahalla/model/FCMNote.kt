package uz.frankie.mahalla.model

data class FCMNote(
    val notification: Notification,
    val registration_ids: ArrayList<String>
)

data class Notification(
    val title: String,
    val body: String
)
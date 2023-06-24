package uz.frankie.mahalla.model

data class FCMNote(
    val api_key: String,
    val notif_body: List<Notification>,
    val body: String,
    val title: String
)

data class Notification(
    val fcm_token: String,
    val neighborhood_id: String
)
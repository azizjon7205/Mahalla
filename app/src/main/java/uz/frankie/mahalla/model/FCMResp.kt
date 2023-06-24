package uz.frankie.mahalla.model

data class FCMResp(
    val canonical_ids: Int,
    val failure: Int,
    val multicast_id: Long,
    val success: Int
)

data class FCMResponse(
    val data: Int? = null,
    val error: Boolean = true
)
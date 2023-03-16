package uz.frankie.mahalla.network.models.population.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NeighborhoodResponse(
    @SerializedName("id") val id: String,
    @SerializedName("Name") val Name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("address") val address: String,
    @SerializedName("category") val category: String,
) : Serializable
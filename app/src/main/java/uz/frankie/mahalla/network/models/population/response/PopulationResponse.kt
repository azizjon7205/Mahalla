package uz.frankie.mahalla.network.models.population.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PopulationResponse(
    @SerializedName("count") val count: Long,
    @SerializedName("result") val result: List<PopulationDataModel>,
) : Serializable

data class PopulationDataModel(
    @SerializedName("id") val id: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("passport") val passport: String,
    @SerializedName("info") val info: String,
    @SerializedName("work_place") val work_place: String,
    @SerializedName("permanent_address") val permanent_address: String,
    @SerializedName("long_gone") val long_gone: String,
    @SerializedName("neighborhood_id") val neighborhood_id: String
) : Serializable
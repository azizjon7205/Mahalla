package uz.frankie.mahalla.network.models.exchangerate.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ExchangeRate(
    @SerializedName("Ccy") val Ccy: String,
    @SerializedName("CcyNm_EN") val CcyNm_EN: String,
    @SerializedName("CcyNm_RU") val CcyNm_RU: String,
    @SerializedName("CcyNm_UZ") val CcyNm_UZ: String,
    @SerializedName("CcyNm_UZC") val CcyNm_UZC: String,
    @SerializedName("Code") val Code: String,
    @SerializedName("Date") val Date: String,
    @SerializedName("Diff") val Diff: String,
    @SerializedName("Nominal") val Nominal: String,
    @SerializedName("Rate") val Rate: String,
    @SerializedName("id") val id: Int
) : Serializable
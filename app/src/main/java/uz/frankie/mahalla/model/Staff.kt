package uz.frankie.mahalla.model

import java.io.Serializable

data class Staff(
    val infoDuty:String,
    val fullname: String,
    val info:String,
    val dateOfBirth:String,
    val province:String,
    val nationality:String,
    val degree:String,
    val major:String,
    val address:String,
    val phonenNumber:String,
    val isMarried:String,
    val image:Int
):Serializable

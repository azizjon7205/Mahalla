package uz.frankie.mahalla.model.village_all_info

import uz.frankie.mahalla.adapter.village.AllInfoType

data class AllInfoDataModel(
    val title: String,
    val infoOne: String,
    val infoTwo: String,
    val subInfo: List<TwoInfoDataModel>,
    val itemType: AllInfoType
)
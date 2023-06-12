package com.marassu.entity.petition

import com.google.gson.annotations.SerializedName

data class PetitionCategory(
//    @SerializedName("petitionCategoryId", alternate = ["id"])
//    val id: String,

    @SerializedName("petitionCategoryName", alternate = ["categoryName"])
    val categoryName: String
)

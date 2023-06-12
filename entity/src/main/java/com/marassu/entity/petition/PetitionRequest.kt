package com.marassu.entity.petition

import com.google.gson.annotations.SerializedName

data class PetitionRequest(
    @SerializedName("petitionTitle")
    val title: String,

    @SerializedName("petitionContent")
    val content: String,

    @SerializedName("category")
    val petitionCategory: List<PetitionCategory>
)
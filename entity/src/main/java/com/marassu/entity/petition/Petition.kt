package com.marassu.entity.petition

import com.google.gson.annotations.SerializedName
import com.marassu.entity.user.User

data class Petition(
    @SerializedName("petitionId")
    val id: String,

    @SerializedName("petitonTitle")
    val titile: String,

    @SerializedName("petitionContent")
    val content: String,

    @SerializedName("petitionImage")
    val imageUrl: String,

    @SerializedName("petitionCategory")
    val categoryList: ArrayList<PetitionCategory>,

    @SerializedName("petitionAgreement")
    val agreement: Long,

    @SerializedName("petitionDisagreement")
    val disagreement: Long,

    @SerializedName("com/marassu/entity/user")
    val user: User
)

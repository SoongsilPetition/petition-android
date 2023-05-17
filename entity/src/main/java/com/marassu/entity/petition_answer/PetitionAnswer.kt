package com.marassu.entity.petition_answer

import com.google.gson.annotations.SerializedName
import com.marassu.entity.user.User

data class PetitionAnswer(
    @SerializedName("petitionAnswerId")
    val id: Long,

    @SerializedName("petitionAnswerTitle")
    val title: String,

    @SerializedName("petitionAnswerContent")
    val content: String,

    @SerializedName("petitionAnswerImage")
    val image: String,

    @SerializedName("com/marassu/entity/user")
    val user: User,

    @SerializedName("petitionId")
    val petitionId: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)

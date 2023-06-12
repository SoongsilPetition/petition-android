package com.marassu.entity.petition

import com.google.gson.annotations.SerializedName
import com.marassu.entity.user.User
import java.io.Serializable


data class Petition(
    @SerializedName("petitionId")
    val id: Long,

    @SerializedName("petitionTitle")
    val title: String,

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

    @SerializedName("user")
    val user: User,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("petitionDueDate")
    val dueDate: String
): Serializable

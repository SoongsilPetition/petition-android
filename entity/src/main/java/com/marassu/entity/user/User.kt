package com.marassu.entity.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("userId")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
): Serializable

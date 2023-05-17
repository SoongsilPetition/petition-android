package com.marassu.entity.user

import com.google.gson.annotations.SerializedName

data class UserRegisterRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("name")
    val name: String
)

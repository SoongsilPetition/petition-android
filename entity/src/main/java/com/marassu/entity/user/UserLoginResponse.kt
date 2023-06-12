package com.marassu.entity.user

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("userId") val userId: Long,
    @SerializedName("jwt") val token: String
)
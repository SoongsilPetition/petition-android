package com.marassu.entity.petition

import com.google.gson.annotations.SerializedName

enum class Sort {
    @SerializedName("createdAt")
    CREATED_AT,

    @SerializedName("agreeCount")
    AGREE_COUNT
}
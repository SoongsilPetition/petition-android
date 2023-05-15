package com.marassu.entity.concur

import com.google.gson.annotations.SerializedName
import com.marassu.entity.user.User

data class Concur(
    @SerializedName("concurId")
    val id: Long,

    @SerializedName("concurContent")
    val content: String,

    @SerializedName("agreementStatus")
    val agreementStatus: AgreementStatus,

    @SerializedName("com/marassu/entity/user")
    val user: User
)
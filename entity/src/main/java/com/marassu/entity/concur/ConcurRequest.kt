package com.marassu.entity.concur

import com.google.gson.annotations.SerializedName

data class ConcurRequest(
    @SerializedName("concurContents")
    val content: String,

    @SerializedName("petitionId")
    val petitionId: Long,

    @SerializedName("agreementStatus")
    val agreementStatus: AgreementStatus
)

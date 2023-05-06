package concur

import com.google.gson.annotations.SerializedName
import user.User

data class Concur(
    @SerializedName("concurId")
    val id: Long,

    @SerializedName("concurContent")
    val content: String,

    @SerializedName("agreementStatus")
    val agreementStatus: AgreementStatus,

    @SerializedName("user")
    val user: User
)
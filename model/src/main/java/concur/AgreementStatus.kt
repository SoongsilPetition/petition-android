package concur

import com.google.gson.annotations.SerializedName

enum class AgreementStatus {
    @SerializedName("AGREE")
    AGREE,
    @SerializedName("DISAGREE")
    DISAGREE
}
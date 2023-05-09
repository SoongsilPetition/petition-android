package petition_answer

import com.google.gson.annotations.SerializedName

data class PetitionAnswerRequest(
    @SerializedName("petitionId")
    val petitionId: Long,

    @SerializedName("petitionAnswerTitle")
    val title: String,

    @SerializedName("petitionAnswerContent")
    val content: String,

    @SerializedName("petitionAnswerImage")
    val image: String

)
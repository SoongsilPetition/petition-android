package petition

import com.google.gson.annotations.SerializedName

data class PetitionCategory(
    @SerializedName("petitionCategoryId")
    val id: String,

    @SerializedName("petitionCategoryName")
    val categoryName: String
)

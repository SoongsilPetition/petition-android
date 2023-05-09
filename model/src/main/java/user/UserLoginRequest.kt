package user

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class UserLoginRequest(
    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)

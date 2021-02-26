package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class LoginResp(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("member") val miembro: Miembro?,
    @SerializedName("sign_in_count") val sign_in_count: Int,
)

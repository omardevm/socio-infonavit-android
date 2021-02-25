package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?,
)
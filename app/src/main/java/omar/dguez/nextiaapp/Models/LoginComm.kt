package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class LoginComm(
    @SerializedName("user") val usuario: Usuario
)
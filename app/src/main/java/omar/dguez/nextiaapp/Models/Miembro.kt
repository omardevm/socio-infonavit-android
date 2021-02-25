package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Miembro (
    @SerializedName("id") val id : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("id_socio_infonavit") val id_socio_infonavit : String,
    @SerializedName("name") val name : String,
    @SerializedName("lastname") val lastname : String,
    @SerializedName("mobile") val mobile : String?,
    @SerializedName("zipcode") val zipcode : String?,
    @SerializedName("avatar") val avatar : String?,
)
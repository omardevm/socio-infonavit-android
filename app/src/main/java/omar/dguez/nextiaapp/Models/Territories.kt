package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Territories(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("clave") val clave: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
)
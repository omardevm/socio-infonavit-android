package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Ally(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("mini_logo_file_name") val mini_logo_file_name: String,
    @SerializedName("mini_logo_full_path") val mini_logo_full_path: String,
)
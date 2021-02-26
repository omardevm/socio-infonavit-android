package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class SubWallet(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("display_text") val display_text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("path") val path: String,
    @SerializedName("primary_color") val primary_color: String,
    @SerializedName("secondary_color") val secondary_color: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("display_index") val display_index: String,
    @SerializedName("avatar") val avatar: Boolean,
    @SerializedName("mobile_cover_url") val mobile_cover_url: Boolean,
    @SerializedName("desktop_cover_url") val desktop_cover_url: Int,
)
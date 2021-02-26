package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Wallet(
    @SerializedName("id") val id: Int,
    @SerializedName("display_index") val display_index: Int,
    @SerializedName("display_text") val display_text: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("path") val path: String?,
    @SerializedName("max_level") val max_level: Int,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("benevit_count") val benevit_count: Int,
    @SerializedName("mobile_cover_url") val mobile_cover_url: String?,
    @SerializedName("desktop_cover_url") val desktop_cover_url: String?,
    @SerializedName("member_level") val member_level: Int,
    @SerializedName("primary_color") val primary_color: String?
)
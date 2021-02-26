package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Locked(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("title") val title: String,
    @SerializedName("instructions") val instructions: String,
    @SerializedName("expiration_date") val expiration_date: String,
    @SerializedName("active") val active: Boolean,
    @SerializedName("primary_color") val primary_color: String,
    @SerializedName("has_coupons") val has_coupons: Boolean,
    @SerializedName("vector_file_name") val vector_file_name: String,
    @SerializedName("vector_full_path") val vector_full_path: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("wallet") val wallet: SubWallet,
    @SerializedName("territories") val territories: List<Territories>,
    @SerializedName("ally") val ally: Ally
)

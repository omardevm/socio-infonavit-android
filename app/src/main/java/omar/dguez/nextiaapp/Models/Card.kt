package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("cardDate") val cardDate: String,
    @SerializedName("cardReach") val cardReach: String,
    @SerializedName("cardDesc") val cardDesc: String,
    @SerializedName("cardImg") val cardImg: String,
    @SerializedName("cardImgBg") val cardImgBg: String,
    @SerializedName("unlocked") val unlocked: Boolean,
)
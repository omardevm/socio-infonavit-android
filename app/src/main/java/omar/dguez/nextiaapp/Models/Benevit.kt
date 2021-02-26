package omar.dguez.nextiaapp.Models

import com.google.gson.annotations.SerializedName

data class Benevit(
    @SerializedName("locked") val locked: List<Locked>,
    @SerializedName("unlocked") val unlocked: List<Unlocked>
)
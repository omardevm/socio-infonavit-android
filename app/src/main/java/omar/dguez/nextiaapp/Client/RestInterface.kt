package omar.dguez.nextiaapp.Client

import okhttp3.ResponseBody
import omar.dguez.nextiaapp.Models.Benevit
import omar.dguez.nextiaapp.Models.LoginComm
import omar.dguez.nextiaapp.Models.LoginResp
import omar.dguez.nextiaapp.Models.Wallet
import retrofit2.Call
import retrofit2.http.*

interface RestInterface {

    /**
     * Login
     */
    @POST("login")
    fun login(@Body request: LoginComm): Call<LoginResp>

    /**
     * Get Wallet
     */
    @GET("member/wallets")
    fun getWallets(): Call<List<Wallet>>

    /**
     * Member Landing
     */
    @GET("member/landing_benevits")
    fun getBenevits(@Header("Authorization") header: String): Call<Benevit>

    /**
     * Logout
     */
    @DELETE("logout")
    fun logout(): Call<ResponseBody>
}
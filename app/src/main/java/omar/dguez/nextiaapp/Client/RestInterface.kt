package omar.dguez.nextiaapp.Client

import okhttp3.ResponseBody
import omar.dguez.nextiaapp.Models.LoginComm
import omar.dguez.nextiaapp.Models.LoginResp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RestInterface {
    /**
     * Login
     * @see data
     */
    @POST("login")
    fun login(@Body request: LoginComm): Call<LoginResp>

    /**
     * Get Movie
     * @see getMovie
     */
    /*@GET("{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
    ): Call<MovieSummary>*/
}
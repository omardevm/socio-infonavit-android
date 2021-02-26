package omar.dguez.nextiaapp.Client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private const val BASE_URL =
        "https://staging.api.socioinfonavit.io/api/v1/"
    private val client = OkHttpClient.Builder().build()
    val instance: RestInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(RestInterface::class.java)
    }
}

package omar.dguez.nextiaapp.Fragments.Wallets

import android.util.Log
import omar.dguez.nextiaapp.Client.RestClient
import omar.dguez.nextiaapp.Models.Benevit
import omar.dguez.nextiaapp.Models.Wallet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WalletsPresenter(val view: WalletsView) {

    private var list: ArrayList<String>? = null

    fun loadWallet() {
        RestClient.instance.getWallets().enqueue(
            object : Callback<List<Wallet>> {
                override fun onResponse(
                    call: Call<List<Wallet>>,
                    response: Response<List<Wallet>>
                ) {
                    if (response.isSuccessful) {
                        val objects = response.body()
                        list = arrayListOf()
                        for (i in objects!!) {
                            list?.add(i.display_text!!)
                        }
                        view.loadViews()
                    } else {
                        view.error("No se pudo cargar")
                    }
                }

                override fun onFailure(call: Call<List<Wallet>>, t: Throwable) {
                    Log.d("RAE", "cannot connect ${t.message}")
                }
            })
    }

    fun loadBenevits(token: String) {
        RestClient.instance.getBenevits(token).enqueue(
            object : Callback<Benevit> {
                override fun onResponse(call: Call<Benevit>, response: Response<Benevit>) {
                    if (response.isSuccessful) {
                        view.loadBenevits(response.body()!!, list)
                    } else {
                        view.error("No se pudo cargar")
                    }
                }

                override fun onFailure(call: Call<Benevit>, t: Throwable) {
                    Log.d("RAE", "unconnect ${t.message}")
                }

            })
    }

}
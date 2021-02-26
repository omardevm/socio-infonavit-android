package omar.dguez.nextiaapp.Fragments.Wallets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import omar.dguez.nextiaapp.Models.*
import omar.dguez.nextiaapp.R
import omar.dguez.nextiaapp.Utils.SharedPrefManager

class Wallets : Fragment(), WalletsView {

    private val presenter = WalletsPresenter(this)
    private var recyclerView: RecyclerView? = null
    private val sectionList: ArrayList<Section> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.loadWallet()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.recyclerView = view.findViewById(R.id.recyclerView)
    }

    override fun loadViews() {
        val token = SharedPrefManager.getInstance(activity!!).fetchAuthToken()!!
        this.presenter.loadBenevits(token)
    }

    override fun loadBenevits(benevits: Benevit, list: ArrayList<String>?) {
        val locked = benevits.locked
        val unlocked = benevits.unlocked
        for (dt in list!!) {
            //dt equivale al nombre del wallet
            for (u in unlocked) {
                val cards: ArrayList<Card> = arrayListOf()
                if (u.wallet.display_text == dt) {
                    cards.add(
                        Card(
                            u.expiration_date,
                            u.territories[0].name,
                            u.description,
                            u.vector_full_path,
                            u.primary_color
                        )
                    )
                }
                sectionList.add(
                    Section(
                        dt, cards
                    )
                )
            }

            for (l in locked) {
                val cards: ArrayList<Card> = arrayListOf()
                if (l.wallet.display_text == dt) {
                    cards.add(
                        Card(
                            l.expiration_date,
                            l.territories[0].name,
                            l.description,
                            l.vector_full_path,
                            l.primary_color
                        )
                    )
                }
                sectionList.add(
                    Section(
                        dt, cards
                    )
                )
            }
        }
        for (i in sectionList) {
            Log.d("RAE", "$i")
        }
    }

    override fun error(msg: String) {
        this.showDialog(
            "No se pudo cargar las carteras",
            "Intente cerrar y abrir su app para buscar nuevas carteras."
        )
    }

    private fun showDialog(title: String, msg: String, signout: Boolean = false) {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle(title)
        builder.setMessage(msg)
        if (signout) {
            // cerrar sesion
        } else {
            builder.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        }
        builder.show()
    }
}
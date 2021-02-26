package omar.dguez.nextiaapp.Fragments.Wallets

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import omar.dguez.nextiaapp.Models.*
import omar.dguez.nextiaapp.R
import omar.dguez.nextiaapp.Utils.MainRecyclerAdapter
import omar.dguez.nextiaapp.Utils.SharedPrefManager
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class Wallets : Fragment(), WalletsView {

    private val presenter = WalletsPresenter(this)
    private val sectionMap: HashMap<String, ArrayList<Card>> = hashMapOf()
    private var mainRecyclerView: RecyclerView? = null
    private var shimmerFrameLayout: ShimmerFrameLayout? = null
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
        shimmerFrameLayout = view.findViewById(R.id.shimmerLayout);
        shimmerFrameLayout?.startShimmer()
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
            sectionMap[dt] = arrayListOf()
            for (u in unlocked) {
                if (u.wallet.display_text == dt) {
                    sectionMap[dt]?.add(
                        Card(
                            "Vence en: ${this.getExpirationDays(u.expiration_date)} días",
                            u.territories[0].name,
                            u.description,
                            u.ally.mini_logo_full_path,
                            u.primary_color,
                            true
                        )
                    )
                }
            }

            for (u in locked) {
                if (u.wallet.display_text == dt) {
                    sectionMap[dt]?.add(
                        Card(
                            u.expiration_date,
                            u.territories[0].name,
                            u.description,
                            u.vector_full_path,
                            u.primary_color,
                            false
                        )
                    )
                }
            }


        }

        mainRecyclerView = activity!!.findViewById(R.id.mainRecyclerView)
        val llm = LinearLayoutManager(activity!!)
        llm.orientation = LinearLayoutManager.VERTICAL
        val lhm = LinearLayoutManager(activity!!)
        lhm.orientation = LinearLayoutManager.HORIZONTAL
        val mainRecyclerAdapter = MainRecyclerAdapter(list, sectionMap)
        mainRecyclerView!!.adapter = mainRecyclerAdapter
        mainRecyclerView!!.layoutManager = llm
        shimmerFrameLayout?.stopShimmer();
        shimmerFrameLayout?.visibility = View.GONE;
        mainRecyclerView!!.addItemDecoration(
            DividerItemDecoration(
                activity!!,
                DividerItemDecoration.HORIZONTAL
            )
        )

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

    @SuppressLint("SimpleDateFormat")
    private fun getExpirationDays(date: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val date1 = sdf.parse(date)
        val date2 = Calendar.getInstance().time
        val diff: Long = date2.time - date1.time
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) * -1
    }
}
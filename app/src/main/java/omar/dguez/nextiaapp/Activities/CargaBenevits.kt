package omar.dguez.nextiaapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import okhttp3.ResponseBody
import omar.dguez.nextiaapp.Client.RestClient
import omar.dguez.nextiaapp.Fragments.Wallets.Wallets
import omar.dguez.nextiaapp.R
import omar.dguez.nextiaapp.Utils.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CargaBenevits : AppCompatActivity(),
    Callback<ResponseBody> {

    private val fr: Fragment = Wallets()
    private var drawer: DrawerLayout? = null
    private var toggle: ActionBarDrawerToggle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga_benevits)
        this.loadBar()
        this.loadFragment(fr, "wallets")
    }

    override fun onStart() {
        super.onStart()
        if (!SharedPrefManager.getInstance(this).isLoggedIn) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun loadBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val logout = findViewById<TextView>(R.id.logout)
        logout.setOnClickListener {
            this.showDialog()

        }
        toolbar.title = null
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        drawer = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer?.addDrawerListener(toggle!!)
        toggle?.syncState()
    }

    override fun onBackPressed() {
        if (drawer!!.isDrawerOpen(GravityCompat.START)) {
            drawer!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun loadFragment(fr: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.benevitContainer, fr, tag)
            .addToBackStack(null).commit()
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            SharedPrefManager.getInstance(applicationContext).clear()
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        Log.d("RAE", "messg ${t.message}")
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea cerrar sesión?")
        builder.setMessage("Seleccione Si o No.")
        builder.setPositiveButton("Si") { dialog, _ ->
            RestClient.instance.logout().enqueue(this)

        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}
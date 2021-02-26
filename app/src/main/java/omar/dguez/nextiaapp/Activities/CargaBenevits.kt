package omar.dguez.nextiaapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import omar.dguez.nextiaapp.Fragments.Wallets.Wallets
import omar.dguez.nextiaapp.R
import omar.dguez.nextiaapp.Utils.SharedPrefManager

class CargaBenevits : AppCompatActivity() {

    private val fr: Fragment = Wallets()

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
        toolbar.title = null
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    private fun loadFragment(fr: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.benevitContainer, fr, tag)
            .addToBackStack(null).commit()
    }
}
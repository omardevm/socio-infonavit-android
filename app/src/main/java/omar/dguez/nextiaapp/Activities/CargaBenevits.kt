package omar.dguez.nextiaapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import omar.dguez.nextiaapp.R
import omar.dguez.nextiaapp.Utils.SharedPrefManager

class CargaBenevits : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carga_benevits)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = null
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

    }

    override fun onStart() {
        super.onStart()
        if (!SharedPrefManager.getInstance(this).isLoggedIn) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
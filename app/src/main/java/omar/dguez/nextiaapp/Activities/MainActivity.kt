package omar.dguez.nextiaapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.concurrent.schedule
import androidx.fragment.app.Fragment
import omar.dguez.nextiaapp.Fragments.Login
import omar.dguez.nextiaapp.Fragments.Splash
import omar.dguez.nextiaapp.R
import java.util.*

class MainActivity : AppCompatActivity() {

    private val splash: Fragment = Splash()
    private val login: Fragment = Login()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.frReplace(this.splash, "splash")
        Timer("Login", false).schedule(3000) {
            frRemove("splash")
            frReplace(login, "login")
        }
    }

    private fun frReplace(fr: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainContainer, fr, tag)
            .commit()
    }

    private fun frRemove(tag: String) {
        supportFragmentManager
            .beginTransaction()
            .remove(supportFragmentManager.findFragmentByTag(tag)!!)
            .commit()
    }
}
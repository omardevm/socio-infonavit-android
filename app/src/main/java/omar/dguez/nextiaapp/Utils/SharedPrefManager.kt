package omar.dguez.nextiaapp.Utils

import android.annotation.SuppressLint
import android.content.Context
import omar.dguez.nextiaapp.Models.Usuario


class SharedPrefManager private constructor(private val mCtx: Context) {
    val sharedPreferences =
        mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    val isLoggedIn: Boolean
        get() {
            return sharedPreferences.getString("email", null) != null
        }

    val usuario: Usuario
        get() {
            return Usuario(
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("password", null)
            )
        }

    fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(SessionManager.USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return sharedPreferences.getString(SessionManager.USER_TOKEN, null)
    }

    fun saveUser(usuario: Usuario) {
        val editor = sharedPreferences.edit()
        editor.putString("email", usuario.email)
        editor.putString("password", usuario.password)
        editor.apply()
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}
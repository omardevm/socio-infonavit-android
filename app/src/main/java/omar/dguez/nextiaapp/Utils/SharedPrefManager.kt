package omar.dguez.nextiaapp.Utils

import android.annotation.SuppressLint
import android.content.Context
import omar.dguez.nextiaapp.Models.LoginResp
import omar.dguez.nextiaapp.Models.Miembro
import omar.dguez.nextiaapp.Models.Usuario


class SharedPrefManager private constructor(mCtx: Context) {
    private val sharedPreferences =
        mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    val isLoggedIn: Boolean
        get() {
            return sharedPreferences.getString("email", null) != null
        }

    val response: LoginResp
        get() {
            return LoginResp(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("role", null),
                Miembro(
                    sharedPreferences.getInt("id", -1),
                    sharedPreferences.getInt("user_id", -1),
                    sharedPreferences.getString("id_socio_infonavit", null),
                    sharedPreferences.getString("name", null),
                    sharedPreferences.getString("lastname", null),
                    sharedPreferences.getString("mobile", null),
                    sharedPreferences.getString("zipcode", null),
                    sharedPreferences.getString("avatar", null),
                ),
                sharedPreferences.getInt("sign_in_count", -1),
            )
        }

    fun saveAuthToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(SharedPrefManager.USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return sharedPreferences.getString(SharedPrefManager.USER_TOKEN, null)
    }

    fun saveResponse(usuario: LoginResp) {
        val editor = sharedPreferences.edit()
        val miembro = usuario.miembro
        editor.putString("email", usuario.email)
        editor.putString("role", usuario.role)
        editor.putString("id_socio_infonavit", miembro?.id_socio_infonavit)
        editor.putString("name", miembro?.name)
        editor.putString("lastname", miembro?.lastname)
        editor.putString("mobile", miembro?.mobile)
        editor.putString("zipcode", miembro?.zipcode)
        editor.putString("avatar", miembro?.avatar)
        editor.putInt("id", usuario.id)
        editor.putInt("user_id", miembro!!.user_id)
        editor.putInt("sign_in_count", usuario.sign_in_count)
        editor.apply()
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        const val SHARED_PREF_NAME = "my_shared_preff"
        const val USER_TOKEN = "user_token"

        @SuppressLint("StaticFieldLeak")
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
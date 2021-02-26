package omar.dguez.nextiaapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import okhttp3.Headers
import omar.dguez.nextiaapp.Activities.CargaBenevits
import omar.dguez.nextiaapp.Client.RestClient
import omar.dguez.nextiaapp.Models.LoginComm
import omar.dguez.nextiaapp.Models.LoginResp
import omar.dguez.nextiaapp.Models.Usuario
import omar.dguez.nextiaapp.R
import omar.dguez.nextiaapp.Utils.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header


class Login : Fragment(), View.OnClickListener, Callback<LoginResp> {

    private var email: EditText? = null
    private var password: EditText? = null
    private var button: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        email = activity?.findViewById(R.id.email)
        password = activity?.findViewById(R.id.password)
        button = activity?.findViewById(R.id.signin)
        //
        email?.addTextChangedListener(loginTextWatcher)
        password?.addTextChangedListener(loginTextWatcher)

        password?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event?.action == KeyEvent.ACTION_DOWN) {
                    when (keyCode) {
                        KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                            button?.callOnClick()
                            return true
                        }
                        else -> {
                        }
                    }
                }
                return false
            }

        })


        button?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == button?.id) {
            RestClient.instance.login(
                LoginComm(
                    Usuario(
                        email?.text.toString(),
                        password?.text.toString()
                    )
                )
            )
                .enqueue(this)
        }
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefManager.getInstance(activity!!).isLoggedIn) {
            activity!!.finish()
            startActivity(Intent(activity!!, CargaBenevits::class.java))
        } else {
            SharedPrefManager.getInstance(activity!!).clear()
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            val strEmail = email?.text.toString().trim()
            val strPass = password?.text.toString().trim()
            button?.isEnabled = strEmail.isNotEmpty() && strPass.isNotEmpty()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    override fun onResponse(call: Call<LoginResp>, response: Response<LoginResp>) {
        if (response.isSuccessful) {
            val headerList: Headers = response.headers()
            val intent = Intent(activity!!, CargaBenevits::class.java)
            var auth = ""
            for (header in headerList) {
                if (header.first == "Authorization") {
                    auth = header.second
                    break
                }
            }
            val instance = SharedPrefManager.getInstance(activity!!)
            instance.saveAuthToken(auth)
            instance.saveResponse(response.body()!!)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        } else {
            this.showDialog()
        }
    }

    override fun onFailure(call: Call<LoginResp>, t: Throwable) {
        Log.d("RAE", "${t.message}")
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Usuario y contraseña incorrectos.")
        builder.setMessage("Intente nuevamente")
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }


}
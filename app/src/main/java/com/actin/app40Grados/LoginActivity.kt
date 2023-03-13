package com.actin.app40Grados

import android.content.Intent
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app40grados.R
import app40grados.databinding.ActivityLoginBinding
import com.actin.app40Grados.aplicacion40Grados.Companion.prefs
/*import com.quickblox.auth.session.QBSettings
import com.quickblox.core.QBEntityCallback
import com.quickblox.core.exception.QBResponseException
import com.quickblox.users.QBUsers
import com.quickblox.users.model.QBUser*/
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class LoginActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //QBSettings.getInstance()

        //printJSON()

        //Checa si ya hay un  usuario Logeado y pasa a la pantalla del codigo qr
        checkUserValues()


        //Listener del boton de login llama a la funcion de login
        binding.loginButton.setOnClickListener {
            if (binding.LoginPasswordEt.text.isNotEmpty() && binding.LoginPasswordEt.text.isNotEmpty()) {
                login()

            }
        }
        //Listener del boton Registrarse, pasa al boton de registrarse
        binding.botonRegistrarse.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
    //Funcion que checa si hay un usuario logeado en las shared preferences, si hay uno pasa a la pantalla del qr
    private fun checkUserValues() {
        if (prefs.getName() != "") {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }
    }


    //Autenticación
    private fun login() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Guarda el texto del correo y de la contraseña en variables
                val correo = binding.LoginEmailEt.text.toString()
                val contraseña = binding.LoginPasswordEt.text.toString()
                //Crea un string buffer
                val datos = StringBuffer()
                //Guarda un URL en la variable y le añade el correo y la contraseña
                val url = URL("http://actinseguro.com/booking/abkcom001.aspx?$correo,$contraseña")
                //Abre la conexion
                val conn = url.openConnection()
                //Lee los datos que regresa el url
                BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                    var line: String?
                    while (inp.readLine().also { line = it } != null) {
                        datos.append(line)
                    }
                }
                //Guarda los datos en una variable
                val datosDelUsuario = datos.toString()
                //println(datosDelUsuario)
                //Convierte los datos en un objeto json
                val jsonObject = JSONObject(datosDelUsuario)

                //quicklogin(correo, contraseña)


                val CIA = jsonObject.getString("CIA")
                //Si el CIA del usuario es diferente de "No encontrado"
                if (CIA != "No Encontrado"){
                    //Guarda las variables que regresa el Json
                    val NOMBRE = jsonObject.getString("NOMBRE")
                    val CORREO = jsonObject.getString("CORREO")
                    var TIPO = jsonObject.getString("TIPO")
                    var ORIGEN = jsonObject.getString("ORIGEN")
                    val ID = jsonObject.getString("ID")
                    val telefono = jsonObject.getString("TELEFONO")
                    val arrayAnuncio = jsonObject.getJSONArray("ANUNCIOS")
                    //Guarda el nombre, el correo, el CIA, El telefon y el ID en las shared preferences
                    prefs.saveName(NOMBRE)
                    prefs.saveCorreo(CORREO)
                    prefs.saveCIA(CIA)
                    prefs.saveTelefono(telefono)
                    prefs.saveID(ID)
                    //Si el Json tienen anuncio pasa a la pantalla de inicio con el valor promo como 1 y le pasa el link del anuncio
                    if(!arrayAnuncio.isNull(0)){
                        val anuncio = arrayAnuncio.getJSONObject(0)
                        val urlAnuncio = anuncio.getString("ANUNCIO")
                        this@LoginActivity.runOnUiThread(java.lang.Runnable{
                            startActivity(Intent(this@LoginActivity, HomeGimnasio::class.java).putExtra("valorPromo", "1").putExtra("promo", urlAnuncio))
                        })
                        //Si no pasa el valor promo como 0
                    }else{
                        startActivity(Intent(this@LoginActivity, HomeGimnasio::class.java).putExtra("valorPromo", "0"))
                    }
                }else {
                    //Si el CIA es "No encontrado" Abre el toast que dice las credenciales no son correctas"
                    this@LoginActivity.runOnUiThread(java.lang.Runnable{
                        Toast.makeText(this@LoginActivity, "Las credenciales ingresadas son incorrectas", Toast.LENGTH_SHORT).show()
                    })
                }
            } catch (ex: Exception) {
                this@LoginActivity.runOnUiThread(java.lang.Runnable {
                    println(ex)
                    Toast.makeText(this@LoginActivity, "Error comunicandose con el servidor, revisa tu conexión a internet.", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}


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

        //Checa si ya hay un  usuario Registrado
        checkUserValues()


        //Listener del boton de login
        binding.loginButton.setOnClickListener {
            if (binding.LoginPasswordEt.text.isNotEmpty() && binding.LoginPasswordEt.text.isNotEmpty()) {
                login()

            }
        }
        //Listener del boton Registrarse
        binding.botonRegistrarse.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun checkUserValues() {
        if (prefs.getName() != "") {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }
    }

    /*private fun quicklogin(username : String, password: String){
        val user = QBUser()
        user.login = username
        user.password = password

        QBUsers.signIn(user).performAsync(object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser?, bundle: Bundle?) {

            }

            override fun onError(exception: QBResponseException?) {

            }
        })

    }*/


    //Autenticación
    private fun login() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val correo = binding.LoginEmailEt.text.toString()
                val contraseña = binding.LoginPasswordEt.text.toString()

                val datos = StringBuffer()

                val url = URL("http://actinseguro.com/booking/abkcom001.aspx?$correo,$contraseña")
                val conn = url.openConnection()
                BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                    var line: String?
                    while (inp.readLine().also { line = it } != null) {
                        datos.append(line)
                    }
                }
                val datosDelUsuario = datos.toString()
                val jsonObject = JSONObject(datosDelUsuario)

                //quicklogin(correo, contraseña)

                val CIA = jsonObject.getString("CIA")
                if (CIA != "No Encontrado"){
                    val NOMBRE = jsonObject.getString("NOMBRE")
                    val CORREO = jsonObject.getString("CORREO")
                    var TIPO = jsonObject.getString("TIPO")
                    var ORIGEN = jsonObject.getString("ORIGEN")
                    val ID = jsonObject.getString("ID")
                    prefs.saveName(NOMBRE)
                    prefs.saveCorreo(CORREO)
                    prefs.saveCIA(CIA)
                    prefs.saveID(ID)
                    this@LoginActivity.runOnUiThread(java.lang.Runnable{
                        startActivity(Intent(this@LoginActivity, HomeGimnasio::class.java))
                    })
                }else {
                    this@LoginActivity.runOnUiThread(java.lang.Runnable{
                        Toast.makeText(this@LoginActivity, "Las credenciales ingresadas son incorrectas", Toast.LENGTH_SHORT).show()
                    })
                }
            } catch (ex: Exception) {
                this@LoginActivity.runOnUiThread(java.lang.Runnable {
                    Toast.makeText(this@LoginActivity, "Error comunicandose con el servidor, revisa tu conexión a internet.", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }



    /*@Table(database = AppDatabase::class)
    class clasesReservadas(@PrimaryKey(autoincrement = true) var id:Long = 0,
                           @Column var fechaclase: String? = null,
                           @Column var maestro: String? = null,
                           @Column var horaclase: String? = null,
                           @Column var salon: String? = null,
                           @Column var tipo: String? = null)*/

    /*fun printJSON() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("http://www.actinseguro.com/cpr/aCPR5082.aspx?5960000001")
            val conn = url.openConnection()

            var datos = StringBuffer()
            BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                var line: String?
                while (inp.readLine().also { line = it } != null) {
                    println(line)
                    datos.append(line)
                }
            }

            val json = datos.toString()
            val JSONObject = JSONObject(json)
            val array = JSONObject.getJSONArray("ENVIO")
            val objeto2 = array.getJSONObject(0)

            val arrayEtiqueta = objeto2.getJSONArray("ETIQUETA")
            val arrayPuntovis = objeto2.getJSONArray("PUNTOVIS")
            val arrayListaind = objeto2.getJSONArray("LISTAIND")

            for (i in (0 until arrayEtiqueta.length())) {
                val objeto = arrayEtiqueta.getJSONObject(i)
                val ETIQTIOTULO = objeto.getString("ETIQTITULO")
                println(ETIQTIOTULO)
            }

            for (i in (0 until arrayPuntovis.length())) {
                val objeto = arrayPuntovis.getJSONObject(i)
                val PTOIO = objeto.getString("PTOID")
                val PTODESC = objeto.getString("PTODESC")
                println(PTOIO)
                println(PTODESC)
                val arrayPTODETItem = objeto.getJSONArray("PTODETItem")
                for (j in (0 until arrayPTODETItem.length())) {
                    val objeto = arrayPTODETItem.getJSONObject(j)
                    val PTODETI = objeto.getString("PTODETI")
                    println(PTODETI)
                }

            }

            for (i in (0 until arrayListaind.length())) {
                val objeto = arrayListaind.getJSONObject(i)
                val LISTAID = objeto.getString("LISTAID")
                val LISTADESC = objeto.getString("LISTADESC")
                val LISTAMAND = objeto.getString("LISTAMAND")
                println(LISTAID)
                println(LISTADESC)
                println(LISTAMAND)
                val arrayListaindic = objeto.getJSONArray("LISTAINDIC")
                for (j in (0 until arrayListaindic.length())) {
                    val objeto = arrayListaindic.getJSONObject(j)
                    val INDIID = objeto.getString("INDIID")
                    val INDIDESC = objeto.getString("INDIDESC")
                    val INDITIPO = objeto.getString("INDITIPO")
                    val INDICICLO = objeto.getString("INDICICLO")
                    val INDMANDAT = objeto.getString("INDMANDAT")
                    val INDIOPCIONES = objeto.getString("INDIOPCIONES")
                    println(INDIID)
                    println(INDIDESC)
                    println(INDITIPO)
                    println(INDICICLO)
                    println(INDMANDAT)
                    println(INDIOPCIONES)
                }
            }
        }
    }*/
}


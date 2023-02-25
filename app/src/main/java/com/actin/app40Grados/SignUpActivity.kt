package com.actin.app40Grados
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import app40grados.R
import app40grados.databinding.ActivitySignUpBinding
//import com.example.appCitas.databinding.ActivitySignUpBinding
//import com.quickblox.core.QBEntityCallback
//import com.quickblox.core.exception.QBResponseException
//import com.quickblox.users.QBUsers
//import com.quickblox.users.model.QBUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class SignUpActivity : AppCompatActivity() {


    //ViewBinding
    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.telefonoET.transformationMethod = null

        binding.creatAccountButton.setOnClickListener{

            if (binding.emailEt.text.isNotEmpty() && binding.passwordEt.text.isNotEmpty()
                && binding.nombreET.text.isNotEmpty() && binding.telefonoET.text.isNotEmpty()){
                if (validaEmail()) {
                    if (binding.passwordEt.text.toString().length >= 8){
                        crearUsuarioGimnasio()
                        binding.telefonoET.text.clear()
                        binding.nombreET.text.clear()
                        binding.emailEt.text.clear()
                        binding.passwordEt.text.clear()
                    }else{
                        Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres.", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Tienes que llenar todos los campos",Toast.LENGTH_SHORT).show()
            }

        }
        binding.cancelButton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }


    fun validaEmail() : Boolean{
        if (Patterns.EMAIL_ADDRESS.matcher(binding.emailEt.text.toString()).matches()){
            return true
        }
        Toast.makeText(this, "La direccion de correo que ingresaste no es valida.", Toast.LENGTH_SHORT).show()
        return false
    }

    /*private fun crearQuickblox(username: String, password: String){
        val user = QBUser()
        user.login = username
        user.password = password

        QBUsers.signUp(user).performAsync(object : QBEntityCallback<QBUser> {
            override fun onSuccess(user: QBUser?, bundle: Bundle?) {
                println("Se creo la cuenta de quickblox")

            }

            override fun onError(exception: QBResponseException?) {
                println("Error al crear la cuenta de quickblox")

            }
        })
    }*/

    private fun crearUsuarioGimnasio() {
        val nombre = binding.nombreET.text.toString()
        val correo = binding.emailEt.text.toString()
        val telefono = binding.telefonoET.text.toString()
        val password = binding.passwordEt.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val jsonUsuarioRegistrado = JSONObject()
                jsonUsuarioRegistrado.put("CIA", "")
                jsonUsuarioRegistrado.put("NOMBRE", "$nombre")
                jsonUsuarioRegistrado.put("CORREO", "$correo")
                jsonUsuarioRegistrado.put("TELEFONO", "$telefono")
                jsonUsuarioRegistrado.put("PAQUETE", "")
                jsonUsuarioRegistrado.put("PASSWORD", "$password")

                //crearQuickblox(correo, password)


                val url = URL("http://actinseguro.com/booking/abkcom002.aspx")
                val postData = jsonUsuarioRegistrado.toString()

                val conn = url.openConnection() as HttpURLConnection
                conn.doInput = true
                conn.doOutput = true
                conn.setRequestProperty("Content-Type" , "application/json")
                conn.setRequestProperty("Accept", "application/json")
                conn.setRequestProperty("Content-Length", postData.length.toString())

                val outputStreamWriter = OutputStreamWriter(conn.getOutputStream())
                outputStreamWriter.write(postData)
                outputStreamWriter.flush()

                var datos = StringBuffer()
                BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                    var line: String?
                    while (inp.readLine().also { line = it } != null) {
                        println(line)
                        datos.append(line)
                    }
                }
                var json = datos.toString()



                when(json){
                    "{\"MSG\":\"Ya existe el correo\"}" -> {
                        this@SignUpActivity.runOnUiThread(java.lang.Runnable{
                            Toast.makeText(this@SignUpActivity, "Ya existe una cuenta con ese correo.", Toast.LENGTH_SHORT).show()
                        })
                }else -> {
                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    this@SignUpActivity.runOnUiThread {
                        Toast.makeText(
                            this@SignUpActivity,
                            "Cuenta creada exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                }

            } catch (ex: Exception) {
                this@SignUpActivity.runOnUiThread(java.lang.Runnable{
                    println(ex)
                    Toast.makeText(this@SignUpActivity, "La cuenta no pudo ser creada", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}
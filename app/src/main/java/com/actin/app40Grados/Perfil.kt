package com.actin.app40Grados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import app40grados.R
import app40grados.databinding.ActivityPerfilBinding
import com.actin.app40Grados.aplicacion40Grados.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Perfil : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.correoUsuarioTv.text = prefs.getCorreo()
        binding.nombreEt.hint = prefs.getName()
        binding.telefonoEt.hint = prefs.getTelefono()
        println(prefs.getTelefono())
        val dias = resources.getStringArray(R.array.Arreglo_Dias)
        val meses = resources.getStringArray(R.array.Arreglo_meses)
        val anos = resources.getStringArray(R.array.Arreglo_anos)
        val diasadapter = ArrayAdapter(this, R.layout.drop_down_item, dias)
        val meseadapter = ArrayAdapter(this, R.layout.drop_down_item, meses)
        val anosadapter = ArrayAdapter(this, R.layout.drop_down_item, anos)

        binding.telefonoEt.transformationMethod = null

        binding.diaACTV.setAdapter(diasadapter)
        binding.mesACTV.setAdapter(meseadapter)
        binding.anoACTV.setAdapter(anosadapter)


        binding.telefonoEt.setOnFocusChangeListener { view, b ->
            if (b){
                binding.telefonoEt.hint = ""
            }else{

            }
        }

        binding.nombreEt.setOnFocusChangeListener { view, b ->
            if (b){
                binding.nombreEt.hint = ""
            }else{

            }
        }


        binding.btnActualizar.setOnClickListener {
            actualizarDatos()
        }
        binding.btnRegresar.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }
    }


    private fun actualizarDatos(){
        CoroutineScope(Dispatchers.IO).launch {
                val email = prefs.getCorreo()
                val nombre = binding.nombreEt.text.toString()
                val telefono = binding.telefonoEt.text.toString()
                val dia = binding.diaACTV.text.toString()
                val mes = binding.mesACTV.text.toString()
                val año = binding.anoACTV.text.toString()
                val fecha = "$dia/$mes/$año"
                val contraseña = validatePassword()
                if (binding.constrasenaNEt.text.isEmpty()){
                    try {
                        if (validateDate() == "vacio"){
                            val objeto = JSONObject("{\"CIA\": \"1\", \"NOMBRE\":\"$nombre\", \"CORREO\":\"$email\", \"TELEFONO\":\"$telefono\", \"PAQUETE\":\"\", \"PASSWORD\":\"\", \"FECHANAC\":\"\"}")
                            val postData = objeto.toString()
                            println(postData)
                            val url = URL("https://actinseguro.com/booking/abkcom016.aspx")
                            val conn = url.openConnection() as HttpsURLConnection
                            conn.doInput = true
                            conn.doOutput = true
                            conn.setRequestProperty("Content-Type" , "application/json")
                            conn.setRequestProperty("Accept", "application/json")
                            val outputStreamWriter = OutputStreamWriter(conn.getOutputStream())
                            outputStreamWriter.write(postData)
                            outputStreamWriter.flush()


                            val dates = StringBuffer()
                            BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                                var line: String?
                                while (inp.readLine().also { line = it } != null) {
                                    dates.append(line)
                                }
                            }
                            val respuesta = dates.toString()
                            println(respuesta)
                            val jsonRespuesta = JSONObject(respuesta)
                            val msg = jsonRespuesta.getString("MSG")
                            if (msg == "Usuario Actualizado"){
                                this@Perfil.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(this@Perfil, "Usuario actualizado exitosamente.", Toast.LENGTH_SHORT).show()
                                    if(nombre != ""){
                                        prefs.saveName(nombre)
                                        binding.nombreEt.hint = nombre
                                    }
                                    if(telefono != ""){
                                        prefs.saveTelefono(telefono)
                                        binding.telefonoEt.hint = telefono
                                    }
                                })
                            }else{
                                this@Perfil.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(this@Perfil, "No se ha podido actualizar los datos.", Toast.LENGTH_SHORT).show()
                                })

                            }
                        }else if (validateDate() == "lleno"){
                            val objeto = JSONObject("{\"CIA\": \"1\", \"NOMBRE\":\"$nombre\", \"CORREO\":\"$email\", \"TELEFONO\":\"$telefono\", \"PAQUETE\":\"\", \"PASSWORD\":\"\", \"FECHANAC\":\"$fecha\"}")
                            val postData = objeto.toString()
                            println(postData)
                            val url = URL("https://actinseguro.com/booking/abkcom016.aspx")
                            val conn = url.openConnection() as HttpsURLConnection
                            conn.doInput = true
                            conn.doOutput = true
                            conn.setRequestProperty("Content-Type" , "application/json")
                            conn.setRequestProperty("Accept", "application/json")

                            val outputStreamWriter = OutputStreamWriter(conn.getOutputStream())
                            outputStreamWriter.write(postData)
                            outputStreamWriter.flush()


                            val dates = StringBuffer()
                            BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                                var line: String?
                                while (inp.readLine().also { line = it } != null) {
                                    dates.append(line)
                                }
                            }
                            val respuesta = dates.toString()
                            println(respuesta)
                            val jsonRespuesta = JSONObject(respuesta)
                            val msg = jsonRespuesta.getString("MSG")
                            if (msg == "Usuario Actualizado"){
                                this@Perfil.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(this@Perfil, "Usuario actualizado exitosamente.", Toast.LENGTH_SHORT).show()
                                    if(nombre != ""){
                                        prefs.saveName(nombre)
                                        binding.nombreEt.hint = nombre
                                    }
                                    if(telefono != ""){
                                        prefs.saveTelefono(telefono)
                                        binding.telefonoEt.hint = telefono
                                    }
                                })
                            }else{
                                this@Perfil.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(this@Perfil, "No se ha podido actualizar los datos.", Toast.LENGTH_SHORT).show()
                                })

                            }
                        }else{
                            this@Perfil.runOnUiThread(java.lang.Runnable {
                                Toast.makeText(this@Perfil, "Fecha ingresada incorrectamente", Toast.LENGTH_SHORT).show()
                            })
                        }
                    }catch (e: Exception){
                        this@Perfil.runOnUiThread(java.lang.Runnable {
                            Toast.makeText(this@Perfil, "Ocurrió un error relizando el cambio", Toast.LENGTH_SHORT).show()
                        })
                    }
                }else{
                    if(contraseña == null){
                        try {
                            val contraseñaN = binding.constrasenaNEt.text.toString()
                            if (validateDate() == "vacio"){
                                val objeto = JSONObject("{\"CIA\": \"1\", \"NOMBRE\":\"$nombre\", \"CORREO\":\"$email\", \"TELEFONO\":\"$telefono\", \"PAQUETE\":\"\", \"PASSWORD\":\"$contraseñaN\", \"FECHANAC\":\"\"}")
                                val postData = objeto.toString()
                                println(postData)
                                val url = URL("https://actinseguro.com/booking/abkcom016.aspx")
                                val conn = url.openConnection() as HttpsURLConnection
                                conn.doInput = true
                                conn.doOutput = true
                                conn.setRequestProperty("Content-Type" , "application/json")
                                conn.setRequestProperty("Accept", "application/json")
                                val outputStreamWriter = OutputStreamWriter(conn.getOutputStream())
                                outputStreamWriter.write(postData)
                                outputStreamWriter.flush()


                                val dates = StringBuffer()
                                BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                                    var line: String?
                                    while (inp.readLine().also { line = it } != null) {
                                        dates.append(line)
                                    }
                                }
                                val respuesta = dates.toString()
                                println(respuesta)
                                val jsonRespuesta = JSONObject(respuesta)
                                val msg = jsonRespuesta.getString("MSG")
                                if (msg == "Usuario Actualizado"){
                                    this@Perfil.runOnUiThread(java.lang.Runnable {
                                        Toast.makeText(this@Perfil, "Usuario actualizado exitosamente.", Toast.LENGTH_SHORT).show()
                                        binding.contrasenaNTIL.helperText = ""
                                        if(nombre != ""){
                                            prefs.saveName(nombre)
                                            binding.nombreEt.hint = nombre
                                        }
                                        if(telefono != ""){
                                            prefs.saveTelefono(telefono)
                                            binding.telefonoEt.hint = telefono
                                        }
                                    })
                                }else{
                                    this@Perfil.runOnUiThread(java.lang.Runnable {
                                        Toast.makeText(this@Perfil, "No se ha podido actualizar los datos.", Toast.LENGTH_SHORT).show()
                                    })

                                }
                            }else if (validateDate() == "lleno"){
                                val objeto = JSONObject("{\"CIA\": \"1\", \"NOMBRE\":\"$nombre\", \"CORREO\":\"$email\", \"TELEFONO\":\"$telefono\", \"PAQUETE\":\"\", \"PASSWORD\":\"$contraseñaN\", \"FECHANAC\":\"$fecha\"}")
                                val postData = objeto.toString()
                                println(postData)
                                val url = URL("https://actinseguro.com/booking/abkcom016.aspx")
                                val conn = url.openConnection() as HttpsURLConnection
                                conn.doInput = true
                                conn.doOutput = true
                                conn.setRequestProperty("Content-Type" , "application/json")
                                conn.setRequestProperty("Accept", "application/json")

                                val outputStreamWriter = OutputStreamWriter(conn.getOutputStream())
                                outputStreamWriter.write(postData)
                                outputStreamWriter.flush()


                                val dates = StringBuffer()
                                BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                                    var line: String?
                                    while (inp.readLine().also { line = it } != null) {
                                        dates.append(line)
                                    }
                                }
                                val respuesta = dates.toString()
                                println(respuesta)
                                val jsonRespuesta = JSONObject(respuesta)
                                val msg = jsonRespuesta.getString("MSG")
                                if (msg == "Usuario Actualizado"){
                                    this@Perfil.runOnUiThread(java.lang.Runnable {
                                        Toast.makeText(this@Perfil, "Usuario actualizado exitosamente.", Toast.LENGTH_SHORT).show()
                                        binding.contrasenaNTIL.helperText = ""
                                        if(nombre != ""){
                                            prefs.saveName(nombre)
                                            binding.nombreEt.hint = nombre
                                        }
                                        if(telefono != ""){
                                            prefs.saveTelefono(telefono)
                                            binding.telefonoEt.hint = telefono
                                        }
                                    })
                                }else{
                                    this@Perfil.runOnUiThread(java.lang.Runnable {
                                        Toast.makeText(this@Perfil, "No se ha podido actualizar los datos.", Toast.LENGTH_SHORT).show()
                                    })

                                }
                            }else{
                                this@Perfil.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(this@Perfil, "Fecha ingresada incorrectamente", Toast.LENGTH_SHORT).show()
                                })
                            }
                        }catch (e: Exception){
                            this@Perfil.runOnUiThread(java.lang.Runnable {
                                Toast.makeText(this@Perfil, "Ocurrió un error relizando el cambio", Toast.LENGTH_SHORT).show()
                            })
                        }
                    }else{
                        this@Perfil.runOnUiThread(java.lang.Runnable {
                            binding.contrasenaNTIL.helperText = contraseña
                        })
                    }
                }
        }
    }

    private fun validatePassword(): String? {
        val passwordtext = binding.constrasenaNEt.text.toString()
        if(passwordtext.length < 8){
            return "La contraseña debe tener mas de 8 caracteres"
        }
        if (!passwordtext.matches(".*[A-Z].*".toRegex())){
            return "La contraseña debe contener al menos una mayuscula."
        }
        if (!passwordtext.matches(".*[a-z].*".toRegex())){
            return "La contraseña debe contener al menos una mayuscula."
        }
        if (!passwordtext.matches(".*[!@#.?].*".toRegex())){
            return "La contraseña debe contener al menos uno de estos caracteres: !@#.?."
        }
        return null
    }

    private fun validateDate(): String{
        if (!binding.diaACTV.text.isEmpty() && !binding.mesACTV.text.isEmpty() && !binding.anoACTV.text.isEmpty()){
            return "lleno"
        }else if (binding.diaACTV.text.isEmpty() && binding.mesACTV.text.isEmpty() && binding.anoACTV.text.isEmpty()){
            return "vacio"
        }else {
            return "incorrecto"
        }
    }
}




package com.actin.app40Grados

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app40grados.R
import app40grados.databinding.ActivityBorrarCuentaBinding
import com.actin.app40Grados.aplicacion40Grados.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class BorrarCuenta : AppCompatActivity() {

    private lateinit var binding: ActivityBorrarCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBorrarCuentaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnno.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }

        binding.btnsi.setOnClickListener {
            llamadaApi()

        }
    }

    private fun llamadaApi(){
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val url = URL("http://www.actinseguro.com/booking/abkcom014.aspx?${prefs.getID()}")
                val conn = url.openConnection()

                val datos = StringBuffer()
                BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                    var line: String?
                    while (inp.readLine().also { line = it } != null) {
                        println(line)
                        datos.append(line)
                    }
                }
                val json = datos.toString()
                val objeto = JSONObject(json)
                val msg = objeto.getString("MSG")
                if (msg == "EL USUARIO HA SIDO ELIMINADO"){
                    startActivity(Intent(this@BorrarCuenta, LoginActivity::class.java))
                    prefs.wipe()
                    runOnUiThread(java.lang.Runnable {
                        Toast.makeText(this@BorrarCuenta, "Tu cuenta ha sido eliminada.", Toast.LENGTH_SHORT).show()
                    })
                }else{
                    runOnUiThread(java.lang.Runnable {
                        Toast.makeText(this@BorrarCuenta, "No se encontro la cuenta.", Toast.LENGTH_SHORT).show()
                    })
                }
            }

        }catch (E: Exception){
            Toast.makeText(this, "Error borrando la cuenta.", Toast.LENGTH_SHORT).show()
        }
    }
}
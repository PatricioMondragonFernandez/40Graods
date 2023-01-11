package com.actin.app40Grados
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.actin.app40Grados.model.clasesReservadas
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class cancelarReservacionDialog(private val Clase: clasesReservadas): AppCompatDialogFragment() {

    lateinit var msg: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        msg = ""
        val builder = AlertDialog.Builder(context)
        builder.setTitle("¿Cancelar Clase?")
            .setMessage("Quieres cancelar la clase de ${Clase.tipoDeClase} con el profesor ${Clase.maestro} el dia ${Clase.fecha}?")
            .setPositiveButton("Sí"){dialogInterface: DialogInterface, i: Int ->
                val calendario = Calendar.getInstance()
                val hora = calendario.get(Calendar.HOUR_OF_DAY).toInt()
                val delim = ":"
                val arreglo = Clase.hora.split(delim)
                val horaClase = arreglo[0].toInt()
                val sfd = SimpleDateFormat("dd/MM/yy")
                val fecha = Clase.fecha
                val currentDate = sfd.format(Date())

                if (horaClase >= 12){
                    if (fecha == currentDate && horaClase - hora < 8){
                        Toast.makeText(context, "Solo puedes cancelar las clases de la tarde con 8 horas de anticipación", Toast.LENGTH_SHORT).show()
                    }else{
                        llamadaApi()
                        runBlocking {
                            launch {
                                delay(1000)
                                if (msg == "la clase ha sido cancelada"){
                                    Toast.makeText(context, "La clase ha sido cancelada exitosamente.", Toast.LENGTH_SHORT).show()

                                }else{
                                    Toast.makeText(context, "Hubo un problema haciendo la cancelación.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }else{
                    if(fecha == currentDate && horaClase - hora < 3 ){
                        Toast.makeText(context, "Solo puedes cancelar las clases de la mañana con 3 horas de anticipación", Toast.LENGTH_SHORT).show()
                    }else{
                        llamadaApi()
                        runBlocking {
                            launch {
                                delay(1000)
                                if (msg == "la clase ha sido cancelada"){
                                    println(msg)
                                    Toast.makeText(context, "La clase ha sido cancelada exitosamente.", Toast.LENGTH_SHORT).show()
                                }else{
                                    println(msg)
                                    Toast.makeText(context, "Hubo un problema cancelando la clase.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
            .setNegativeButton("No"){dialogInterface:DialogInterface, i: Int ->}

        return builder.create()
    }

    private fun llamadaApi(){
        CoroutineScope(Dispatchers.IO).launch{
            val url = URL("http://www.actinseguro.com/booking/abkcom009.aspx?1,${aplicacion40Grados.prefs.getID()}, ${Clase.id}, ${Clase.fecha},${Clase.idR}")
            val conn = url.openConnection()

            var datos = StringBuffer()
            BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                var line: String?
                while (inp.readLine().also { line = it } != null) {
                    println(line)
                    datos.append(line)
                }
                val json = datos.toString()
                val objetoJson = JSONObject(json)
                val array = objetoJson.getJSONArray("CLASE_CANCELADA")
                val objeto = array.getJSONObject(0)
                val status = objeto.getString("CLASE_ACCION")
                msg = status
                println(objetoJson)
                println(array)
                println(msg)

            }
        }
    }
}
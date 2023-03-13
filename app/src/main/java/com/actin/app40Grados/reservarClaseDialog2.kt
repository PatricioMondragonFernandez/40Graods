package com.actin.app40Grados
import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import com.actin.app40Grados.model.Clases
import com.actin.app40Grados.aplicacion40Grados.Companion.prefs
import com.actin.app40Grados.model.clasesSemana
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class reservarClaseDialog2(private val Clase: clasesSemana): AppCompatDialogFragment() {
    private lateinit var msg: String


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        msg = ""
        val cupo = Clase.cupo.toInt()
        val ocupado = Clase.ocupado.toInt()
        println(prefs.getID())
        builder.setTitle("¿Reservar clase?")
            .setMessage("¿Reservar la clase de ${Clase.nombreClase} el dia ${Clase.fecha}, de ${Clase.hora}?. Quedan ${cupo - ocupado} lugares.")
            .setPositiveButton("Si") { dialogInterface: DialogInterface, i: Int ->
                val calendario = Calendar.getInstance()
                val hora = calendario.get(Calendar.HOUR_OF_DAY).toInt()
                val delim = ":"
                val arreglo = Clase.hora.split(delim)
                val horaClase = arreglo[0].toInt()
                val sfd = SimpleDateFormat("dd/MM/yyyy")
                val currentDate = sfd.format(Date())

                if (Clase.fecha == currentDate && (horaClase-1) < hora){
                    Toast.makeText(context, "Es muy tarde para reservar esa clase.", Toast.LENGTH_SHORT).show()
                }else{
                    if (cupo - ocupado <= 0){
                        Toast.makeText(context, "Ya no hay lugares para esta clase.", Toast.LENGTH_SHORT).show()
                    }else{
                        llamadaApi()
                        runBlocking {
                            launch {
                                delay(1000)
                                println("este es el mensaje $msg")
                                if (msg == "El Registro se guardo correctamente"){
                                    Toast.makeText(context, "Clase registrada correctamente.", Toast.LENGTH_SHORT).show()
                                    //createNotificationChannel()
                                    //scheduleNotification()
                                }else if(msg == "El usuario no cuenta con paquete asignado"){
                                    Toast.makeText(context, "No cuentas con un paquete asignado.", Toast.LENGTH_SHORT).show()
                                }else if(msg == "El ultimo paquete registrado ha expirado") {
                                    Toast.makeText(context, "El ultimo paquete registrado ha expirado", Toast.LENGTH_LONG).show()
                                } else if(msg == "El tipo de salon de la clase no concuerda con el salon del paquete"){
                                    Toast.makeText(context, "El Registro se guardo correctamente El tipo de salon de la clase no consuerda con el salón del paquete", Toast.LENGTH_LONG).show()


                                }else{
                                    Toast.makeText(context, "No pudo realizarse la reservación.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
            .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int -> dismiss() }

        return builder.create()
    }


    private fun scheduleNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val intent = Intent( context?.applicationContext, Notificaciones::class.java)
            val title = "Alarma Clase"
            val message = "Tu clase de ${Clase.nombreClase} empieza en una hora"
            intent.putExtra(titleExtra, title)
            intent.putExtra(messageExtra, message)

            val pendingIntent = PendingIntent.getBroadcast(context?.applicationContext,
                notificacionId,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

            val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val time = fecha()
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, time, pendingIntent
            )
        }
    }



    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val nombre = "notifChannel"
            val desc = "A description of the Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, nombre, importance)
            channel.description = desc
            val notificationManager =context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


    private fun fecha(): Long{
        val delimf = "/"
        val delimh = ":"
        val arregloF = Clase.fecha.split(delimf)
        val arregloH = Clase.hora.split(delimh)

        val diaS = arregloF[0].toString()
        val dia = diaS.toInt()
        val mesS = arregloF[1].toString()
        val mes = mesS.toInt() -1
        val añoS = arregloF[2].toString()
        val año = añoS.toInt()

        val horaS = arregloH[0].toString()
        val hora = horaS.toInt() - 1
        val minuto = 0


        val calendario = Calendar.getInstance()
        calendario.set(año, mes, dia, hora, minuto)
        println(calendario.time)

        return calendario.timeInMillis
    }

    private fun llamadaApi(){
        CoroutineScope(Dispatchers.IO).launch {
            val objeto = JSONObject("{\"REGISTRO_CLASE\":[{\"CIA\":\"${prefs.getCIA()}\", \"ID_CLASE\":\"${Clase.idClase}\", \"ID_SALON_LUGAR\": \"${Clase.SalonId}\", \"ID_USUARIO\":\"${prefs.getID()}\", \"RESERVA_DIA\":\"${Clase.fecha}\" }]}")
            println(objeto)
            val url = URL("http://actinseguro.com/booking/abkcom007.aspx")
            val postData = objeto.toString()

            val conn = url.openConnection() as HttpURLConnection
            conn.doInput = true
            conn.doOutput = true
            conn.setRequestProperty("Content-Type" , "application/json")
            conn.setRequestProperty("Accept", "application/json")
            conn.setRequestProperty("Content-Length", postData.length.toString())

            val outputStreamWriter = OutputStreamWriter(conn.getOutputStream())
            outputStreamWriter.write(postData)
            outputStreamWriter.flush()

            val datos = StringBuffer()
            BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                var line: String?
                while (inp.readLine().also { line = it } != null) {
                    println(line)
                    datos.append(line)
                }
            }
            val respuesta = datos.toString()
            val jsonRespuesta = JSONObject(respuesta)
            val respuestaArray = jsonRespuesta.getJSONArray("RESPONSE")
            val objetoArray = respuestaArray.getJSONObject(0)
            msg = objetoArray.getString("MSG")
        }
    }
}
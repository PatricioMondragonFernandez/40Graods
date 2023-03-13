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
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

class reservarClaseDialog(private val Clase: Clases, private val idSalonClase: String): AppCompatDialogFragment() {
    private lateinit var msg: String


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        //Se inicializa la variable de mensaje que regresara el api
        msg = ""
        //Se guarda el cupo y los lugares ocupados de la clase recibida en variables
        val cupo = Clase.cupo.toInt()
        val ocupado = Clase.ocupado.toInt()
        //println(prefs.getID())
        //Se crea el dialog
        builder.setTitle("¿Reservar clase?")
                //Se le pone el mensaje con los datos de la clase recibida
            .setMessage("¿Reservar la clase de ${Clase.tipoDeClase} con el profesor ${Clase.maestro} el dia ${Clase.fecha} ?. Quedan ${cupo - ocupado} lugares.")
                //Se añade el boton de si y si el usuario lo clickea se hace la siguiente logica
            .setPositiveButton("Si") { dialogInterface: DialogInterface, i: Int ->
                if (cupo - ocupado <= 0){
                    //si ya no hay lugares se hace un toast que dice que ya no hay lugares
                    Toast.makeText(context, "Ya no hay lugares para esta clase.", Toast.LENGTH_SHORT).show()
                }else{
                    //Si hay lugares se hace una llamada al Api para que se reserve la clase
                    llamadaApi()
                    runBlocking {
                        launch {
                            delay(1000)
                            //Este delay es para dar tiempo de que llegue el mensaje
                            println("este es el mensaje $msg")
                            //Si el mensaje es este se indica que la clase se registro correctamente
                            if (msg == "El Registro se guardo correctamente"){
                                Toast.makeText(context, "Clase registrada correctamente.", Toast.LENGTH_SHORT).show()
                                //createNotificationChannel()
                                //scheduleNotification()
                                //Si este es el mensaje se muestra este toast al usuraio
                            }else if(msg == "El usuario no cuenta con paquete asignado"){
                                Toast.makeText(context, "No cuentas con un paquete asignado.", Toast.LENGTH_SHORT).show()
                                //Si el mensaje es este se mostrara el siguiente toast
                            }else if(msg == "El ultimo paquete registrado ha expirado"){
                                Toast.makeText(context, "El ultimo paquete registrado ha expirado", Toast.LENGTH_SHORT).show()
                                //Si el mensaje es este se mostrara el siguiente toast
                            }else if(msg == "El tipo de salon de la clase no concuerda con el salon del paquete"){
                                Toast.makeText(context, "El tipo de salon de la clase no concuerda con el salón del paquete", Toast.LENGTH_LONG).show()

                            }else{
                                Toast.makeText(context, "No pudo hacerse la reservación es posible que no cuentes con un paquete asignado o ya tengas una reservación para esa clase.", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
                //Si el usuario presiona el boton de no el dilogo desaparece
            .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int -> }

        return builder.create()
    }


    /*private fun scheduleNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val intent = Intent( context?.applicationContext, Notificaciones::class.java)
            val title = "Alarma Clase"
            val message = "Tu clase de ${Clase.tipoDeClase} empieza en una hora"
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
    }*/


    /*private fun fecha(): Long{
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
    }*/
    //Funcion que hace la llamada para registrar la clase
    private fun llamadaApi(){
        val job = CoroutineScope(Dispatchers.IO).launch {
            //Se crea el Json con los datos de la clase recibida
            val objeto = JSONObject("{\"REGISTRO_CLASE\":[{\"CIA\":\"${prefs.getCIA()}\", \"ID_CLASE\":\"${Clase.id}\", \"ID_SALON_LUGAR\": \"$idSalonClase\", \"ID_USUARIO\":\"${prefs.getID()}\", \"RESERVA_DIA\":\"${Clase.fecha}\" }]}")
            println(objeto)
            //Se crea la variable del url, se hace la conexion y se recibe el mensaje
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
            //Se guarda la respuetsa en la variable mensaje
            msg = objetoArray.getString("MSG")
        }
        runBlocking { job.join() }
    }
}
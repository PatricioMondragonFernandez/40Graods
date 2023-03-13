package com.actin.app40Grados
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.ByteArrayOutputStream
import android.util.Base64
import android.widget.TextView
import android.widget.Toast
import app40grados.R
import com.actin.app40Grados.aplicacion40Grados.Companion.prefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class BienvenidoFragment : Fragment() {
    private lateinit var imageViewCodigo : ImageView
    private lateinit var textViewClasesRestantes: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_bienvenido_, container, false)
        //Inicia Variables de componentes
        imageViewCodigo = view.findViewById(R.id.QrIv)
        textViewClasesRestantes = view.findViewById(R.id.tvClasesRestantes)
        //Crear Codigo Qr de usuario
        val usuario = prefs.getID()
        //Inicia la matriz de bits
        try {
            //Crea una instancia del codificador de texto
            val encoder = BarcodeEncoder()
            //Inicia el bitmap del codigo qr que se añadira en el image view
            val bitMap = encoder.encodeBitmap( usuario, BarcodeFormat.QR_CODE, 1000, 1000)
            //Poner el Bitmap en el image view
                imageViewCodigo.setImageBitmap(bitMap)
            //LLama a la funcion para pasar la imagen del codigo qr a base 64(Aun no se usa)
            encode()
        }catch (e: WriterException){

        }
        //Llamada api para ver el paquete del usuario y las clase que le quedan
        llamadaApi()
        return view
    }
    //para pasar la imgagen del codigo qr a base 64(aun no se usa)
    private fun encode(){
        val byteArrayOutputStream = ByteArrayOutputStream()
        val bitmap: Bitmap = imageViewCodigo.drawable.toBitmap()

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageBytes : ByteArray = byteArrayOutputStream.toByteArray()
        val imageString: String = Base64.encodeToString(imageBytes, Base64.DEFAULT)

        //println(imageString)
    }
    //Regresa el paquete que tiene el usuario
    private fun llamadaApi(){
        CoroutineScope(Dispatchers.IO).launch{
            try{
                val url = URL("https://www.actinseguro.com/booking/abkcom010.aspx?1,${prefs.getID()}")
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
                println(json)
                //Agarra el paquete que tiene la person y las clase que le quedan
                val objeto = JSONObject(json)
                val arrayJson = objeto.getJSONArray("PAQUETE")
                val indice1 = arrayJson.getJSONObject(0)
                val CIA = indice1.getString("CIA")
                val clasesRestantes = indice1.getString("CLASES_RESTANTES")
                val paquete = indice1.getString("PAQUETE")
                val vigencia = indice1.getString("CLASE_VIGENCIA")
                println(json)
                //Si no hay un paqute registrado se muestra "No tienes un paquete registrado" en el textview
                if (CIA == "No Hay paquete registrado"){
                    activity?.runOnUiThread(java.lang.Runnable{
                        textViewClasesRestantes.text = "No tienes un paquete registrado."
                    })
                    //Si el paquete es de auto pago solo se muestra el nombre del paquete
                }else if(clasesRestantes == "Paquete ilimitado o de autopago"){
                    activity?.runOnUiThread(java.lang.Runnable{
                        textViewClasesRestantes.text = "$paquete"
                    })
                }else{
                    //Si es un paquete diferente se muestran las clases restante y el nombre del pquete
                    activity?.runOnUiThread(java.lang.Runnable{
                        textViewClasesRestantes.text = "Te quedan $clasesRestantes clases de tu Paquete: $paquete"
                    })
                }
            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    println(ex)
                    Toast.makeText(context, "Error Mostrando el paquete, revisa tu conexión.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        }
    }
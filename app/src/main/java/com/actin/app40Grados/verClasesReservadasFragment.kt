package com.actin.app40Grados
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app40grados.R
import com.actin.app40Grados.ViewModel.ViewModelClasesReservadas
import com.actin.app40Grados.model.clasesReservadas
import com.actin.app40Grados.aplicacion40Grados.Companion.prefs
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class verClasesReservadasFragment : Fragment() {
    private lateinit var listaClases:MutableList<clasesReservadas>
    private lateinit var listaVacia:MutableList<clasesReservadas>
    private lateinit var clasesRecyclerview: RecyclerView
    private lateinit var textView: TextView
    private lateinit var dayString: String
    private val clasesReservadasViewModel : ViewModelClasesReservadas by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ver_clases_reservadas, container, false)
        clasesRecyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewClasesReservadas)

        listaClases = mutableListOf<clasesReservadas>()
        listaVacia = mutableListOf<clasesReservadas>()

        textView = view.findViewById<TextView>(R.id.noClasesTv)

        println(prefs.getID())

        llamadaApi()

        clasesReservadasViewModel.clasesReservadasModel.observe(viewLifecycleOwner, androidx.lifecycle.Observer { lista ->
            clasesRecyclerview.layoutManager = LinearLayoutManager(context)
            clasesRecyclerview.setHasFixedSize(false)
            var lista2 = mutableListOf<clasesReservadas>()
            for (i in (0 until lista.size)){
                if (lista[i].mostrar == 1){
                    lista2.add(lista[i])
                }
            }
            clasesRecyclerview.adapter = clasesReservadasAdapter(lista2, {onItemSelected(it)})
            textView.visibility = View.GONE
        })

        return view
    }


    private fun onItemSelected(Clase: clasesReservadas) {
        val dialog = cancelarReservacionDialog(Clase)
        dialog.show(parentFragmentManager, "Cancelar Reservacion Dialog")
    }

    override fun onResume() {
        super.onResume()
        llamadaApi()
    }
    private fun llamadaApi(){
        CoroutineScope(Dispatchers.IO).launch{
            try{
                listaClases.clear()
                val url = URL("http://www.actinseguro.com/booking/abkcom008.aspx?1,${prefs.getID()}")
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
                val arrayJson = objeto.getJSONArray("CLASES_RESERVADAS")
                val objetoindice1 = arrayJson.getJSONObject(0)
                if (objetoindice1.getString("CIA") != "No Hay Clases registradas"){
                    for (i in (0 until arrayJson.length())){
                        val objeto = arrayJson.getJSONObject(i)
                        var CIA = objeto.getString("CIA")
                        val nombreClase = objeto.get("CLASE_NOMBRE")
                        val horaClase = objeto.getString("CLASE_HORARIO")
                        val idClase = objeto.getString("CLASE_ID_CLASE")
                        val fecha = objeto.getString("CLASE_DIA")
                        val  idR = objeto.getString("RESERVA_ID")
                        val claseResponsable = objeto.getString("CLASE_RESPONSABLE")
                        val delim = "/"
                        val componentes = fecha.toString().split(delim)
                        val day = componentes[0].toInt()
                        val month = componentes[1].toInt()
                        val year = componentes[2].toInt()
                        val simpleDateFormat = SimpleDateFormat("EEEE")
                        val date = Date(year + 100, month - 1, day)
                        println(date)
                        dayString = simpleDateFormat.format(date)
                        println(dayString)
                        when (claseResponsable) {
                            "LAURA"->{
                                listaClases.add(i, clasesReservadas("Laura", "$horaClase", R.drawable.laura1, "$nombreClase", idClase, fecha,idR, 1))
                            }"OLGA BELICHKO"->{
                            listaClases.add(i, clasesReservadas("Olga Belichko", "$horaClase", R.drawable.olga, "$nombreClase", idClase, fecha,idR, 1))
                        }"YURI CIENFUEGOS"->{
                            listaClases.add(i, clasesReservadas("Yuri Cienfuegos", "$horaClase", R.drawable.yuri, "$nombreClase", idClase, fecha,idR,1))
                        }"ADRIANA GARIBAY"->{
                            listaClases.add(i, clasesReservadas("Adriana Garibay", "$horaClase", R.drawable.adriana, "$nombreClase", idClase, fecha,idR,1))
                        }"JUAN CALDERON"->{
                            listaClases.add(i, clasesReservadas("Juan Calderon", "$horaClase", R.drawable.juan, "$nombreClase", idClase, fecha,idR,1))
                        }"BRIGGITE SCHEPERS"->{
                            listaClases.add(i, clasesReservadas("Brigitte Schepers", "$horaClase", R.drawable.bridgitte, "$nombreClase", idClase, fecha,idR,1))
                        }"LILIANA TORRIJOS"->{
                            listaClases.add(i, clasesReservadas("Liliana Torrijos", "$horaClase", R.drawable.liliana, "$nombreClase", idClase, fecha,idR,1))
                        }"GIAN FRANCO"->{
                            listaClases.add(i, clasesReservadas("Gian Franco", "$horaClase", R.drawable.gianfranco, "$nombreClase", idClase, fecha,idR,1))
                        }else->{
                            listaClases.add(i, clasesReservadas("", "$horaClase", R.drawable.fondo_blanco, "$nombreClase", idClase, fecha,idR,1))
                        }
                        }
                    }
                    clasesReservadasViewModel.addClases(listaClases)
                }else{
                    withContext(Dispatchers.Main){
                        listaClases.clear()
                        clasesReservadasViewModel.addClases(listaVacia)
                        textView.visibility = View.VISIBLE
                    }
                }
            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Error mostrando las clases reservadas, revisa tu conexión.", Toast.LENGTH_SHORT).show()
                    textView.visibility = View.GONE
                }
            }
        }
    }
}
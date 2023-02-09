package com.actin.app40Grados

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app40grados.R
import com.actin.app40Grados.model.Clases
import com.actin.app40Grados.adapterClases.clasesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class reservarClaseFragment : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var FechaET: EditText
    private lateinit var dropdownmenu: AutoCompleteTextView
    private lateinit var dayString: String

    //inicializa recyclerview
    private lateinit var clasesRecyclerview: RecyclerView
    private lateinit var listaClases: MutableList<Clases>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reservar_clase, container, false)
        //Edit text
        FechaET = view.findViewById<EditText>(R.id.FechaET)
        //drop down menu
        dropdownmenu = view.findViewById<AutoCompleteTextView>(R.id.localizacionesACTV)
        val salones = resources.getStringArray(R.array.Areglo_Salones)
        val ddmadapter = ArrayAdapter(requireContext(), R.layout.drop_down_item, salones)
        dropdownmenu.setAdapter(ddmadapter)
        dropdownmenu.onItemClickListener = this@reservarClaseFragment
        //inicializa recyclerview
        clasesRecyclerview = view.findViewById<RecyclerView>(R.id.recyclerViewClasesDisponibles)
        //inicializa la lista de las clases que se recibirán
        listaClases = mutableListOf<Clases>()



        FechaET.setOnClickListener {
            showDatePickerDialog()
        }

        return view
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(parentFragmentManager, "Selecciona una fecha")
    }

    //Ejecuta codigo cuando se selecciona una fecha
    @SuppressLint("SuspiciousIndentation")
    fun onDateSelected(day: Int, month: Int, year: Int) {
        val month1 = String.format("%02d", month + 1)
        val day1 = String.format("%02d", day)
        val simpleDateFormat = SimpleDateFormat("EEEE")
        val date = Date(day - 1, month, year)
        dayString = simpleDateFormat.format(date)
            FechaET.setText("$year/$month1/$day1")

            if (dropdownmenu.text.toString() == "Clase con calor") {
                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        listaClases.clear()
                        val url =
                            URL("http://www.actinseguro.com/booking/abkcom003.aspx?1,2,$day/$month1/$year")
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
                        val arrayJson = objeto.getJSONArray("CLASES_POR_SALON_LUGAR")
                        println(arrayJson)
                        for (i in (0 until arrayJson.length())) {
                            val objeto = arrayJson.getJSONObject(i)
                            if (objeto.get("CIA") == "No Hay Clases para esa fecha") {
                                activity?.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(
                                        context,
                                        "No hay clases para ese dia, en ese salón.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                })
                            } else {
                                val nombreClase = objeto.get("CLASE_NOMBRE")
                                val horaClase = objeto.getString("CLASE_HORARIO")
                                val idClase = objeto.getString("CLASE_ID_CLASE")
                                val cupo = objeto.getString("CLASE_CUPO_MAX")
                                val ocupado = objeto.getString("CLASE_OCUPADO")
                                val maestro = objeto.getString("CLASE_RESPONSABLE")
                                println(dayString)
                                when(maestro){
                                    "ADRIANA GARIBAY"->{
                                        listaClases.add(i, Clases("Adriana Garibay", horaClase, R.drawable.adriana, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                    }"LAURA"->{
                                    listaClases.add(i, Clases("Laura", horaClase, R.drawable.laura1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"YURI CIENFUEGOS"->{
                                    listaClases.add(i, Clases("Yuri Cienfuegos", horaClase, R.drawable.yuri, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"OLGA BELICHKO"->{
                                    listaClases.add(i, Clases("Olga Belichko", horaClase, R.drawable.olga, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"BRIGGITE SCHEPERS"->{
                                    listaClases.add(i, Clases("Brigitte Schepers", horaClase, R.drawable.bridgitte, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"JUAN CALDERON"->{
                                    listaClases.add(i, Clases("Juan Calderon", horaClase, R.drawable.juan, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"LILI"->{
                                    listaClases.add(i, Clases("Lili", horaClase, R.drawable.liliana, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"SYLVIA MONSALVE"->{
                                    listaClases.add(i, Clases("Sylvia Monsalve", horaClase, R.drawable.sylvya1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))

                                }"LILIANA TORRIJOS"->{
                                    listaClases.add(i, Clases("Liliana Torrijos", horaClase, R.drawable.sylvya1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"GIAN FRANCO"->{
                                    listaClases.add(i, Clases("Gian Franco", horaClase, R.drawable.gianfranco, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }else->{
                                    listaClases.add(i, Clases("", horaClase, R.drawable.fondo_blanco, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }
                                }
                            }
                        }
                        activity?.runOnUiThread(java.lang.Runnable {
                            clasesRecyclerview.layoutManager = LinearLayoutManager(context)
                            clasesRecyclerview.setHasFixedSize(false)
                            clasesRecyclerview.adapter = clasesAdapter(listaClases, { onItemSelected(it) })
                        })
                    }catch (ex: Exception){
                        activity?.runOnUiThread(java.lang.Runnable {
                            Toast.makeText(context, "Error Mostrando la clase, revisa tu conexión.", Toast.LENGTH_SHORT).show()
                        })

                    }
                }

            } else if (dropdownmenu.text.toString() == "Clase sin calor") {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        listaClases.clear()
                        val url =
                            URL("http://www.actinseguro.com/booking/abkcom003.aspx?1,1,$day/$month1/$year")
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
                        val objeto = JSONObject(json)
                        val arrayJson = objeto.getJSONArray("CLASES_POR_SALON_LUGAR")
                        println(arrayJson)
                        for (i in (0 until arrayJson.length())) {
                            val objeto = arrayJson.getJSONObject(i)
                            if (objeto.getString("CIA") == "No Hay Clases para esa fecha") {
                                activity?.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(
                                        context,
                                        "No hay clases para ese dia, en ese salón.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                })
                            } else {
                                val nombreClase = objeto.get("CLASE_NOMBRE")
                                val horaClase = objeto.getString("CLASE_HORARIO")
                                val idClase = objeto.getString("CLASE_ID_CLASE")
                                val cupo = objeto.getString("CLASE_CUPO_MAX")
                                val ocupado = objeto.getString("CLASE_OCUPADO")
                                val maestro = objeto.getString("CLASE_RESPONSABLE")
                                println(dayString)
                                when(maestro){
                                    "ADRIANA GARIBAY"->{
                                        listaClases.add(i, Clases("Adriana Garibay", horaClase, R.drawable.adriana, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                    }"LAURA"->{
                                    listaClases.add(i, Clases("Laura", horaClase, R.drawable.laura1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"YURI CIENFUEGOS"->{
                                    listaClases.add(i, Clases("Yuri Cienfuegos", horaClase, R.drawable.yuri, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"OLGA BELICHKO"->{
                                    listaClases.add(i, Clases("Olga Belichko", horaClase, R.drawable.olga, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"BRIGGITE SCHEPERS"->{
                                    listaClases.add(i, Clases("Brigitte Schepers", horaClase, R.drawable.bridgitte, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"JUAN CALDERON"->{
                                    listaClases.add(i, Clases("Juan Calderon", horaClase, R.drawable.juan, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"LILI"->{
                                    listaClases.add(i, Clases("Lili", horaClase, R.drawable.laura1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"SYLVIA MONSALVE"->{
                                    listaClases.add(i, Clases("Sylvia Monsalve", horaClase, R.drawable.sylvya1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))

                                }else->{
                                    listaClases.add(i, Clases("", horaClase, R.drawable.fondo_blanco, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }
                                }
                            }
                        }
                        activity?.runOnUiThread(java.lang.Runnable {
                            clasesRecyclerview.layoutManager = LinearLayoutManager(context)
                            clasesRecyclerview.setHasFixedSize(false)
                            clasesRecyclerview.adapter =
                                clasesAdapter(listaClases, { onItemSelected(it) })
                        })
                    }catch (ex: Exception){
                        activity?.runOnUiThread(java.lang.Runnable {
                            Toast.makeText(context, "Error mostrando las clases, revisa tu conexión.", Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            } else {

            }

    }

    fun onItemSelected(Clase: Clases) {
        if (dropdownmenu.text.toString() == "Clase con calor") {
            val dialog = reservarClaseDialog(Clase, "2")
            dialog.show(parentFragmentManager, "reservarclasedialog")
        } else if (dropdownmenu.text.toString() == "Clase sin calor") {
            val dialog = reservarClaseDialog(Clase, "1")
            dialog.show(parentFragmentManager, "reservarclasedialog")
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0) {
            if (FechaET.text.toString() == "") {

            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        listaClases.clear()
                        val url =
                            URL("http://www.actinseguro.com/booking/abkcom003.aspx?1,2,${FechaET.text.toString()}")
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
                        val objeto = JSONObject(json)
                        val arrayJson = objeto.getJSONArray("CLASES_POR_SALON_LUGAR")
                        println(arrayJson)
                        for (i in (0 until arrayJson.length())) {
                            val objeto = arrayJson.getJSONObject(i)
                            if (objeto.get("CIA") == "No Hay Clases para esa fecha") {
                                activity?.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(
                                        context,
                                        "No hay clases para ese dia, en ese salón.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                })
                            } else {
                                val nombreClase = objeto.get("CLASE_NOMBRE")
                                val horaClase = objeto.getString("CLASE_HORARIO")
                                val idClase = objeto.getString("CLASE_ID_CLASE")
                                val cupo = objeto.getString("CLASE_CUPO_MAX")
                                val ocupado = objeto.getString("CLASE_OCUPADO")
                                val maestro = objeto.get("CLASE_RESPONSABLE")
                                when(maestro){
                                    "ADRIANA GARIBAY"->{
                                        listaClases.add(i, Clases("Adriana Garibay", horaClase, R.drawable.adriana, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                    }"LAURA"->{
                                    listaClases.add(i, Clases("Laura", horaClase, R.drawable.laura1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"YURI CIENFUEGOS"->{
                                    listaClases.add(i, Clases("Yuri Cienfuegos", horaClase, R.drawable.yuri, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"OLGA BELICHKO"->{
                                    listaClases.add(i, Clases("Olga Belichko", horaClase, R.drawable.olga, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"BRIGGITE SCHEPERS"->{
                                    listaClases.add(i, Clases("Brigitte Schepers", horaClase, R.drawable.bridgitte, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"JUAN CALDERON"->{
                                    listaClases.add(i, Clases("Juan Calderon", horaClase, R.drawable.juan, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"LILI"->{
                                    listaClases.add(i, Clases("Lili", horaClase, R.drawable.laura1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"SYLVIA MONSALVE"->{
                                    listaClases.add(i, Clases("Sylvia Monsalve", horaClase, R.drawable.sylvya1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))

                                }else->{
                                    listaClases.add(i, Clases("", horaClase, R.drawable.fondo_blanco, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }
                                }

                            }
                        }
                        activity?.runOnUiThread(java.lang.Runnable {
                            clasesRecyclerview.layoutManager = LinearLayoutManager(context)
                            clasesRecyclerview.setHasFixedSize(false)
                            clasesRecyclerview.adapter =
                                clasesAdapter(listaClases, {onItemSelected(it) })
                        })
                    }catch(ex : Exception){
                        activity?.runOnUiThread(java.lang.Runnable {
                            Toast.makeText(context, "Error mostrando las clases, revisa tu conexión.", Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            }
        } else if (position == 1) {
            if (FechaET.text.toString() == "") {

            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        listaClases.clear()
                        val url =
                            URL("http://www.actinseguro.com/booking/abkcom003.aspx?1,1,${FechaET.text.toString()}")
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
                        val objeto = JSONObject(json)
                        val arrayJson = objeto.getJSONArray("CLASES_POR_SALON_LUGAR")
                        println(arrayJson)
                        for (i in (0 until arrayJson.length())) {
                            val objeto = arrayJson.getJSONObject(i)
                            if (objeto.getString("CIA") == "No Hay Clases para esa fecha") {
                                activity?.runOnUiThread(java.lang.Runnable {
                                    Toast.makeText(
                                        context,
                                        "No hay clases para ese dia, en ese salón.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                })

                            } else {
                                val nombreClase = objeto.get("CLASE_NOMBRE")
                                val horaClase = objeto.getString("CLASE_HORARIO")
                                val idClase = objeto.getString("CLASE_ID_CLASE")
                                val cupo = objeto.getString("CLASE_CUPO_MAX")
                                val ocupado = objeto.getString("CLASE_OCUPADO")
                                val maestro = objeto.getString("CLASE_RESPONSABLE")
                                when(maestro){
                                    "ADRIANA GARIBAY"->{
                                        listaClases.add(i, Clases("Adriana Garibay", horaClase, R.drawable.adriana, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                    }"LAURA"->{
                                    listaClases.add(i, Clases("Laura", horaClase, R.drawable.laura1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"YURI CIENFUEGOS"->{
                                    listaClases.add(i, Clases("Yuri Cienfuegos", horaClase, R.drawable.yuri, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"OLGA BELICHKO"->{
                                    listaClases.add(i, Clases("Olga Belichko", horaClase, R.drawable.olga, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"BRIGGITE SCHEPERS"->{
                                    listaClases.add(i, Clases("Brigitte Schepers", horaClase, R.drawable.bridgitte, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"JUAN CALDERON"->{
                                    listaClases.add(i, Clases("Juan Calderon", horaClase, R.drawable.juan, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"LILI"->{
                                    listaClases.add(i, Clases("Lili", horaClase, R.drawable.laura1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }"SYLVIA MONSALVE"->{
                                    listaClases.add(i, Clases("Sylvia Monsalve", horaClase, R.drawable.sylvya1, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }else->{
                                    listaClases.add(i, Clases("", horaClase, R.drawable.fondo_blanco, "$nombreClase", idClase, FechaET.text.toString(), cupo, ocupado))
                                }
                                }
                            }
                            activity?.runOnUiThread(java.lang.Runnable {
                                clasesRecyclerview.layoutManager = LinearLayoutManager(context)
                                clasesRecyclerview.setHasFixedSize(false)
                                clasesRecyclerview.adapter = clasesAdapter(listaClases, { onItemSelected(it) })
                            })
                        }
                    }catch (ex: Exception){
                        activity?.runOnUiThread(java.lang.Runnable {
                            Toast.makeText(context, "Error mostrando las clases, revisa tu conexión.", Toast.LENGTH_SHORT).show()
                        })
                    }
                }
            }
        }
    }
}
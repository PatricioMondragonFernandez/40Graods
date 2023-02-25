package com.actin.app40Grados
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.pdf.PdfRenderer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import app40grados.R
import app40grados.databinding.ActivityHomeGimnasioBinding
import com.actin.app40Grados.DB.clasesSemanaDB
import com.actin.app40Grados.ViewModel.ViewModelClasesReservadas
import com.actin.app40Grados.model.clasesReservadas
import com.actin.app40Grados.aplicacion40Grados.Companion.prefs
import com.actin.app40Grados.model.clasesSemana
import com.google.android.material.tabs.TabLayout
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class HomeGimnasio : AppCompatActivity(), OnDateSelectedListener {

    private lateinit var binding: ActivityHomeGimnasioBinding
    private lateinit var listaClases:MutableList<clasesReservadas>
    private val clasesReservadasViewModel: ViewModelClasesReservadas by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_gimnasio)
        binding = ActivityHomeGimnasioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //value recibe el valor de el boton de reservar de la pantalla nuestras clases y manda al usuario hasta el fragmento de reservar las clases
        val value = intent.getStringExtra("valor")
        //promoValor recibe un valor despues del login para mostrar promocion si es "1".
        val valorPromo = intent.getStringExtra("valorPromo")

        if (valorPromo == "1"){
            val timer = object: CountDownTimer(6000, 6000) {
                override fun onTick(millisUntilFinished: Long) {
                    binding.wvPromocion.visibility = View.VISIBLE
                    val promo = intent.getStringExtra("promo").toString()
                    println(promo)
                    binding.wvPromocion.webViewClient = WebViewClient()
                    /*binding.wvPromocion.settings.builtInZoomControls = true
                    binding.wvPromocion.settings.loadWithOverviewMode = true
                    binding.wvPromocion.settings.useWideViewPort = true*/
                    binding.wvPromocion.setInitialScale(100)
                    binding.wvPromocion.loadUrl("https://docs.google.com/gview?embedded=true&url=$promo")
                }

                override fun onFinish() {
                    binding.wvPromocion.visibility = View.GONE
                }
            }
            timer.start()
        }



        //Inicializacion de la lista de clases
        listaClases = mutableListOf<clasesReservadas>()

        //Set up del TabLayout
        setUpTabBar()

        //llamada para poner la lista de clases en el calendario
        llamadaApi()

        clasesReservadasViewModel.clasesReservadasModel.observe(this, Observer{


        })


        //On click listener del
        binding.calendarioIB.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            binding.viewcalendario.visibility = View.VISIBLE
            binding.viewblnca.visibility = View.VISIBLE
        }
        //On click listener para salirse del calendario
        binding.viewcalendario.setOnClickListener {
            binding.calendarView.visibility = View.GONE
            binding.viewcalendario.visibility = View.GONE
            binding.viewblnca.visibility = View.GONE
        }


        binding.calendarView.setOnDateChangedListener { calendarView, calendarDay, b ->
            onDateSelected(calendarView, calendarDay, b)
        }
        if (value == "1"){
            binding.tabLayout.selectTab(binding.tabLayout.getTabAt(2))
        }
        binding.menuIv.setOnClickListener{

            val view = View.inflate(this@HomeGimnasio, R.layout.dialog_menu, null)

            val builder = AlertDialog.Builder(this@HomeGimnasio)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP

            val btnHorarios = view.findViewById<Button>(R.id.btnHorariosDialog)
            val btnSalones = view.findViewById<Button>(R.id.btnSalonesDialog)
            val btnUbicanos = view.findViewById<Button>(R.id.btnUbícanosDialog)
            val btnVerPaquetes = view.findViewById<Button>(R.id.btnPaqueteDialog)
            val btnLogout = view.findViewById<Button>(R.id.btnLogoutDialog)
            val btnConocenos = view.findViewById<Button>(R.id.btnConocenosDialog)
            val btnNuestrasClases = view.findViewById<Button>(R.id.btnNuestrasClases)
            val btnGaleria = view.findViewById<Button>(R.id.btnGaleria)
            val btnEliminarCuenta = view.findViewById<Button>(R.id.eliminarCuenta)
            val btnPerfil = view.findViewById<Button>(R.id.btnPerfil)

            btnHorarios.setOnClickListener {
                startActivity(Intent(this, horarios::class.java))

            }
            btnSalones.setOnClickListener {
                startActivity(Intent(this, DescripcionSalones::class.java))
            }
            btnUbicanos.setOnClickListener {
                startActivity(Intent(this, Ubicaciones::class.java))
            }
            btnVerPaquetes.setOnClickListener {
                startActivity(Intent(this, PaquetesActivity::class.java))
            }
            btnLogout.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
                prefs.wipe()
            }
            btnConocenos.setOnClickListener {
                startActivity(Intent(this, Conocenos::class.java))
            }
            btnNuestrasClases.setOnClickListener {
                startActivity(Intent(this, NuestrasClases::class.java))
            }
            btnGaleria.setOnClickListener {
                startActivity(Intent(this, Galeria::class.java))
            }
            btnEliminarCuenta.setOnClickListener {
                startActivity(Intent(this, BorrarCuenta::class.java))
            }
            btnPerfil.setOnClickListener {
                startActivity(Intent(this, Perfil::class.java))
            }
        }
        binding.nombreTv.setText(prefs.getName())
    }


    //Hace toda la logica del menu de navegacion inferior
    private fun setUpTabBar() {
        //Crea un adaptador
        val adapter = TabPageAdapter(this, binding.tabLayout.tabCount)

        binding.viewPager.adapter = adapter

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
                if (position == 0){
                    binding.nombreTv.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.BienvenidoTv.setTextColor(Color.parseColor("#FFFFFF"))
                    binding.imagenChica.visibility = View.VISIBLE
                    binding.calendarioIB.visibility = View.GONE
                }else{
                    binding.BienvenidoTv.setTextColor(Color.parseColor("#000000"))
                    binding.nombreTv.setTextColor(Color.parseColor("#961A1A"))
                    binding.imagenChica.visibility = View.INVISIBLE
                    binding.calendarioIB.visibility = View.VISIBLE
                    llamadaApi()
                }
            }
        })

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun llamadaApi(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
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
                println(json)
                val arrayJson = objeto.getJSONArray("CLASES_RESERVADAS")
                val objetoindice1 = arrayJson.getJSONObject(0)
                if (objetoindice1.getString("CIA") != "No Hay Clases registradas"){
                    listaClases.clear()
                    for (i in (0 until arrayJson.length())){
                        val objeto = arrayJson.getJSONObject(i)
                        var CIA = objeto.getString("CIA")
                        val nombreClase = objeto.get("CLASE_NOMBRE")
                        val horaClase = objeto.getString("CLASE_HORARIO")
                        val idClase = objeto.getString("CLASE_ID_CLASE")
                        val fecha = objeto.getString("CLASE_DIA")
                        val  idR = objeto.getString("RESERVA_ID")
                        val claseResponsable = objeto.getString("CLASE_RESPONSABLE")
                        when (claseResponsable) {
                            "LAURA"->{
                                listaClases.add(i, clasesReservadas("Laura", horaClase, R.drawable.laura1, "$nombreClase", idClase, fecha,idR,1))
                            }"OLGA BELICHKO"->{
                            listaClases.add(i, clasesReservadas("Olga Belichko", horaClase, R.drawable.olga, "$nombreClase", idClase, fecha,idR,1))
                        }"YURI CIENFUEGOS"->{
                            listaClases.add(i, clasesReservadas("Yuri Cienfuegos", horaClase, R.drawable.yuri, "$nombreClase", idClase, fecha,idR,1))
                        }"ADRIANA GARIBAY"->{
                            listaClases.add(i, clasesReservadas("Adriana Garibay", horaClase, R.drawable.adriana, "$nombreClase", idClase, fecha,idR,1))
                        }"JUAN CALDERON"->{
                            listaClases.add(i, clasesReservadas("Juan Calderon", horaClase, R.drawable.juan, "$nombreClase", idClase, fecha,idR,1))
                        }"BRIGGITE SCHEPERS"->{
                            listaClases.add(i, clasesReservadas("Brigitte Schepers", horaClase, R.drawable.bridgitte, "$nombreClase", idClase, fecha,idR,1))
                        }else->{
                            listaClases.add(i, clasesReservadas("Sylvia Monsalve", horaClase, R.drawable.sylvya1, "$nombreClase", idClase, fecha,idR,1))
                        }

                        }
                    }
                    for (i in (0 until listaClases.size)){
                        println(listaClases[i].fecha)
                        val fecha = listaClases[i].fecha
                        val delim = "/"
                        val fecha1 = fecha.split(delim)
                        val año = fecha1[2]
                        val mes = fecha1[1]
                        val dia = fecha1[0]
                        val ano = "20$año"
                        val fecha2 = "$ano/$mes/$dia"
                        val mydate = CalendarDay.from(ano.toInt(),mes.toInt(),dia.toInt())
                        withContext(Dispatchers.Main){
                            binding.calendarView.addDecorators(CurrentDayDecorator(this@HomeGimnasio, mydate))
                        }
                    }
                }else{

                }
            }catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@HomeGimnasio,"Error mostrando las clases reservadas, revisa tu conexión.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDateSelected(p0: MaterialCalendarView, p1: CalendarDay, p2: Boolean) {
        val delim2 = "}"
        val delim1 = "{"
        val delim = "-"
        val string = p1.toString()
        val list = string.split(delim, delim1, delim2)
        val year = list[1]
        val month = list[2]
        val day = list[3]
        var fechaSelec: String
        if (day.toInt() >= 10 && month.toInt() >= 10){
            fechaSelec = "$day/$month/$year"
        }else if (day.toInt() < 10 && month.toInt() < 10){
            fechaSelec = "0$day/0$month/$year"
        }else if (day.toInt() < 10 && month.toInt() >= 10){
            fechaSelec = "0$day/$month/$year"
        }else{
            fechaSelec = "$day/0$month/$year"
        }
        println(listaClases.size)
        for (i in (0 until listaClases.size)){
            val delim = "/"
            val list = listaClases[i].fecha.split(delim)
            val ano = list[2]
            val dia = list[0]
            val mes = list[1]
            val fechaClases = "$dia/$mes/20$ano"
            println(fechaClases)
            println(fechaSelec)
            if (fechaSelec != fechaClases){
                listaClases[i].mostrar = 0
            }else{
                listaClases[i].mostrar = 1
            }
        }
        println(listaClases)
        clasesReservadasViewModel.addClases(listaClases)
        binding.calendarView.visibility = View.GONE
        binding.viewcalendario.visibility = View.GONE
        binding.viewblnca.visibility = View.GONE
    }
}
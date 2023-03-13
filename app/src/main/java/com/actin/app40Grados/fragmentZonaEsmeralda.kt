package com.actin.app40Grados
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import app40grados.R

//Frgment de zona esmeralda para abrir el mapa
class fragmentZonaEsmeralda : Fragment() {
    private lateinit var btn : Button
    private lateinit var btnWaze:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_zona_esmeralda, container, false)
        //Variables de los botones
        btn = view.findViewById(R.id.abrBtnZonaEsmeralda)

        btnWaze = view.findViewById(R.id.abrBtnWazeZonaEsmeralda)
        //link de ubicacion en waze
        val uri = "https://waze.com/ul?q=Plaza%20La%20Cantera"
        //boton que abre el mapa de google maps
        btn.setOnClickListener {
            startActivity(Intent(context, actividadMapa::class.java).putExtra("Ubicacion", "1"))
        }
        //Boton que abre waze con el link
        btnWaze.setOnClickListener {
            startActivity(Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)))
        }




        return view
    }
}
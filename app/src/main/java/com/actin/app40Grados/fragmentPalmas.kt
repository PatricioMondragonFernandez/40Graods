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


class fragmentPalmas : Fragment() {

    private lateinit var btnAbrirMapa: Button

    private lateinit var btnAbrirWaze: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_palmas, container, false)
        //Fragment de palmas, desabilitado temporalmente

        btnAbrirMapa = view.findViewById(R.id.abrirBtnPalmas)
        btnAbrirWaze = view.findViewById(R.id.abrirWazeBtnPalmas)

        val uri = "https://waze.com/ul?q=Palmas%20Uno"

        /*btnAbrirMapa.setOnClickListener {
            startActivity(Intent(context, actividadMapa::class.java).putExtra("Ubicacion", "2"))
        }*/

        /*btnAbrirWaze.setOnClickListener {
            startActivity(Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)))
        }*/



        return view
    }
}
package com.actin.app40Grados
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app40grados.R
import app40grados.databinding.ActivityActividadMapaZonaEsmeraldaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class actividadMapa : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map : GoogleMap
    private lateinit var binding: ActivityActividadMapaZonaEsmeraldaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActividadMapaZonaEsmeraldaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCloseMapa.setOnClickListener {
            startActivity(Intent(this, Ubicaciones::class.java))
        }

        createFragment()


    }

    private fun createFragment() {
        val mapFragment: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()

    }

    private fun createMarker() {
        val ubicacion = intent.getStringExtra("Ubicacion")
        if (ubicacion == "1"){
            val coordenadas = LatLng(19.55248, -99.27089)
            val marker = MarkerOptions().position(coordenadas).title("¡Aqui estamos!")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordenadas,18f),
                3000,null
            )
        }else{
            val coordenadas = LatLng(19.39321192876095, -99.28183433720301)
            val marker = MarkerOptions().position(coordenadas).title("¡Aqui estamos!")
            map.addMarker(marker)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(coordenadas,18f),
                3000,null
            )

        }

    }
}
package com.actin.app40Grados
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import app40grados.R
import app40grados.databinding.ActivityUbicacionesBinding

class Ubicaciones : FragmentActivity() {

    private lateinit var binding: ActivityUbicacionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUbicacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(fragmentZonaEsmeralda())

        binding.imgBtnEsmeralda.setOnClickListener{
            replaceFragment(fragmentZonaEsmeralda())
            binding.imgBtnEsmeralda.setImageResource(R.drawable.esmeralda_on)
            binding.imgBtnPalmas.setImageResource(R.drawable.proximamente_off)

        }

        binding.imgBtnPalmas.setOnClickListener {
            replaceFragment(fragmentPalmas())
            binding.imgBtnPalmas.setImageResource(R.drawable.proximamente_on)
            binding.imgBtnEsmeralda.setImageResource(R.drawable.esmeralda_off)


        }

        binding.cancelButtonUbicaciones.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }

    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerUbicaciones, fragment)
        fragmentTransaction.commit()
    }
}
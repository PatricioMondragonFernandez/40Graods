package com.actin.app40Grados
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app40grados.R
import app40grados.databinding.ActivityDescripcionSalonesBinding

class DescripcionSalones : AppCompatActivity() {

    private lateinit var binding: ActivityDescripcionSalonesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescripcionSalonesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var contador = 0
        binding.btnSalones.setOnClickListener {
            contador += 1
            if ( contador%2 == 0){
                binding.btnSalones.setImageResource(R.drawable.boton_con_calor)
                binding.imViewSalones.setImageResource(R.drawable.salon_caliente)
                binding.tVSalones.text = getString(R.string.SalonCaliente)
            }else {
                binding.btnSalones.setImageResource(R.drawable.boton_sin_calor)
                binding.imViewSalones.setImageResource(R.drawable.salon_tradicional)
                binding.tVSalones.text = getString(R.string.SalonTradicional)
            }
        }

        binding.btnCloseSalones.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }

        binding.btnReservarSalones.setOnClickListener{
            startActivity(Intent(this, HomeGimnasio::class.java).putExtra("valor", "1"))

        }


    }
}
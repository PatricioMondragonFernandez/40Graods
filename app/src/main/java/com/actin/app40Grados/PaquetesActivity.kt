package com.actin.app40Grados
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app40grados.databinding.ActivityPaquetesBinding

class PaquetesActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPaquetesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaquetesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgbtnback.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }
    }
}
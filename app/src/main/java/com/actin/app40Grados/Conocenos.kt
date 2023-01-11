package com.actin.app40Grados
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app40grados.databinding.ActivityConocenosBinding

class Conocenos : AppCompatActivity() {

    lateinit var binding: ActivityConocenosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConocenosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCloseConocenos.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }

    }
}
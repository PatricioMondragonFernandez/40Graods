package com.actin.app40Grados
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import app40grados.R
import app40grados.databinding.ActivityGaleriaBinding
//Pantalla de la galeria de fotos
class Galeria : AppCompatActivity() {

    private lateinit var binding: ActivityGaleriaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGaleriaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancelGaleria.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }
        //Listener de la imagen 1, se abre un dialog con la imagen para que se vea mas grande,
        // asi funcionan todos los listeners de las imagenes
        binding.imagen1.setOnClickListener {
            val view = View.inflate(this@Galeria, R.layout.d_imagen1, null)

            val builder = AlertDialog.Builder(this@Galeria)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCanceledOnTouchOutside(true)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP
        }
        binding.imagen2.setOnClickListener {
            val view = View.inflate(this@Galeria, R.layout.d_imagen2, null)

            val builder = AlertDialog.Builder(this@Galeria)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCanceledOnTouchOutside(true)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP
        }

        binding.imagen3.setOnClickListener {
            val view = View.inflate(this@Galeria, R.layout.d_imagen3, null)

            val builder = AlertDialog.Builder(this@Galeria)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCanceledOnTouchOutside(true)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP
        }

        binding.imagen4.setOnClickListener {
            val view = View.inflate(this@Galeria, R.layout.d_imagen4, null)

            val builder = AlertDialog.Builder(this@Galeria)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCanceledOnTouchOutside(true)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP
        }
        binding.imagen5.setOnClickListener {
            val view = View.inflate(this@Galeria, R.layout.d_imagen5, null)

            val builder = AlertDialog.Builder(this@Galeria)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCanceledOnTouchOutside(true)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP
        }
        binding.imagen6.setOnClickListener{
            val view = View.inflate(this@Galeria, R.layout.d_imagen6, null)

            val builder = AlertDialog.Builder(this@Galeria)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCanceledOnTouchOutside(true)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP
        }

        binding.imagen7.setOnClickListener{
            val view = View.inflate(this@Galeria, R.layout.d_imagen7, null)

            val builder = AlertDialog.Builder(this@Galeria)
            builder.setView(view)


            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCanceledOnTouchOutside(true)
            val window = dialog.window
            val windowP = window?.attributes
            windowP?.gravity = Gravity.TOP
            window?.attributes = windowP
        }
    }
}
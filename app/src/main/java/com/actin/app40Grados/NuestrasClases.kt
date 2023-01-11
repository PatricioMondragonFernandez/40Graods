package com.actin.app40Grados
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import app40grados.R
import app40grados.databinding.ActivityNuestrasClasesBinding

class NuestrasClases : AppCompatActivity() {
    lateinit var binding: ActivityNuestrasClasesBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuestrasClasesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnYogaKids.width = getWidthButtons()
        binding.btnYogaWheel.width = getWidthButtons()
        binding.btnBikram.width = getWidthButtons()
        binding.btnHotTRX.width = getWidthButtons()
        binding.btnHotVinyasa.width = getWidthButtons()
        binding.btnAntiGravityN.width = getWidthButtons()
        binding.btnPowerWheel.width = getWidthButtons()

        binding.btnHotVinyasa.backgroundTintList = getColorStateList(android.R.color.darker_gray)

        binding.btnHatha.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnHatha.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.hatha)
            binding.tvNuestrasClases.text = getString(R.string.Hatha)
        }

        binding.btnHotVinyasa.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnHotVinyasa.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.hotvinyasa)
            binding.tvNuestrasClases.text = getString(R.string.HotVinyasa)
        }
        binding.btnHotTRX.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnHotTRX.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.trx_android)
            binding.tvNuestrasClases.text = getString(R.string.HotTrx)
        }
        binding.btnBikram.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnBikram.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.bikram)
            binding.tvNuestrasClases.text = getString(R.string.Bikram)
        }
        binding.btnYogaWheel.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnYogaWheel.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.rueda)
            binding.tvNuestrasClases.text = getString(R.string.YogaWheel)
        }
        binding.btnYogaKids.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnYogaKids.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.yogakids)
            binding.tvNuestrasClases.text = getString(R.string.YogaKids)
        }
        binding.btnAntiGravityN.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnAntiGravityN.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.antigravity)
            binding.tvNuestrasClases.text = getString(R.string.AntiGravity)
        }
        binding.btnPowerWheel.setOnClickListener{
            cambiarAspectoBtns()
            binding.btnPowerWheel.backgroundTintList = getColorStateList(android.R.color.darker_gray)
            binding.imVClases.setImageResource(R.drawable.powerwheelandroid)
            binding.tvNuestrasClases.text = getString(R.string.powerWheel)
        }
        binding.btnNuestrasClases.setOnClickListener{
            startActivity(Intent(this, HomeGimnasio::class.java).putExtra("valor", "1"))
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cambiarAspectoBtns(){
        val array = arrayOf(binding.btnYogaKids, binding.btnBikram, binding.btnHotVinyasa, binding.btnHotTRX, binding.btnAntiGravityN, binding.btnYogaWheel, binding.btnPowerWheel, binding.btnHatha)
        for (i in (0 until array.size)){
            array[i].backgroundTintList= getColorStateList(android.R.color.white)
            }
        }

    private fun getWidthButtons(): Int{
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
            val windowMetrics = this.windowManager.currentWindowMetrics

            val width = ((windowMetrics.bounds.width()*0.80)/2)-10
            val width1 = width.toInt()
            return width1

        }else{
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            val width = ((displayMetrics.widthPixels * 0.80)/ 2)-10
            val width1 = width.toInt()
            return width1
        }
    }
}
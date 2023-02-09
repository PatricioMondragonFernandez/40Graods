package com.actin.app40Grados
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import app40grados.R
import app40grados.databinding.ActivityHorariosBinding
import com.actin.app40Grados.model.Clases
import com.actin.app40Grados.model.clasesSemana
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

class horarios : AppCompatActivity() {

    private lateinit var binding : ActivityHorariosBinding
    private lateinit var listaClasesSemana: MutableList<clasesSemana>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        llamadaApiC()

        binding = ActivityHorariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in (0 until listaClasesSemana.size)){
            when(listaClasesSemana[i].DOW){
                "L"->{
                    when(listaClasesSemana[i].hora){
                        "06:00 am - 07:00 am"->{
                            when(listaClasesSemana[i].nombreClase){
                                "BIKRAM 60 min." ->{
                                    binding.M6A.setBackgroundResource(R.drawable.bikram60)
                                }"HOT VINYASA" ->{
                                binding.M6A.setBackgroundResource(R.drawable.hotvinyasac)
                                }"HATHA"->{
                                binding.M6A.setBackgroundResource(R.drawable.hathacuadrito)
                                }"ANTI GRAVITY"->{
                                binding.M6A.setBackgroundResource(R.drawable.antigravityc)
                                }"HOT TRX"->{
                                binding.M6A.setBackgroundResource(R.drawable.hottrxc)
                                }"YOGA WHEEL"->{
                                binding.M6A.setBackgroundResource(R.drawable.yogawheelc)
                                }"YOGA KIDS"->{
                                binding.M6A.setBackgroundResource(R.drawable.yogakidsc)
                                }"BIKRAM 90 min."->{
                                    binding.M6A.setBackgroundResource(R.drawable.bikram90)
                                }"POWER WHEEL"->{
                                    binding.M6A.setBackgroundResource(R.drawable.power_wheel)
                                }
                            }
                        }"07:15 am - 08:15 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M7A.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M7A.setBackgroundResource(R.drawable.hotvinyasac)
                            }"HATHA"->{
                            binding.M7A.setBackgroundResource(R.drawable.hathacuadrito)
                            }"ANTI GRAVITY"->{
                            binding.M7A.setBackgroundResource(R.drawable.antigravityc)
                            }"HOT TRX"->{
                            binding.M7A.setBackgroundResource(R.drawable.hottrxc)
                            }"YOGA WHEEL"->{
                            binding.M7A.setBackgroundResource(R.drawable.yogawheelc)
                            }"YOGA KIDS"->{
                            binding.M7A.setBackgroundResource(R.drawable.yogakidsc)
                            }"BIKRAM 90 min."->{
                            binding.M7A.setBackgroundResource(R.drawable.bikram90)
                            }"POWER WHEEL"->{
                            binding.M7A.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"08:00 am - 09:00 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M8A.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M8A.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M8A.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M8A.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M8A.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M8A.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M8A.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M8A.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M8A.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"07:00 pm - 08:00 pm"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M7P.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M7P.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M7P.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M7P.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M7P.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M7P.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M7P.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M7P.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M7P.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"06:00 pm - 07:00 pm"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M6P2.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M6P2.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M6P.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M6P.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M6P2.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M6P2.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M6P.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M6P2.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M6P2.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"07:30 pm - 09:00 pm"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M73P.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M73P.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M73P.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M73P.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M73P.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M73P.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M73P.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M73P.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M73P.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"11:30 am - 12:30 am" ->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M6P2.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M6P2.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M6P.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M6P.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M6P2.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M6P2.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M6P.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M6P2.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M6P2.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"09:15 am - 10:45 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M6P2.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M6P2.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M6P.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M6P.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M6P2.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M6P2.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M6P.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M6P2.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M6P2.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"07:30 pm - 8:30 pm"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M73P.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M73P.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M73P.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M73P.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M73P.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M73P.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M73P.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M73P.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M73P.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                        }"05:00 pm - 07:00 pm"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.M5P.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.M5P.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.M5P.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.M5P.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.M5P.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.M5P.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.M5P.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.M5P.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.M5P.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                    }
                    }
                }"M"->{
                when(listaClasesSemana[i].hora){
                    "06:00 am - 07:00 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.T6A.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.T6A.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.T6A.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.T6A.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.T6A.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.T6A.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.T6A.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.T6A.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.T6A.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                    }"07:15 am - 08:15 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T7A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T7A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T7A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T7A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T7A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T7A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T7A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T7A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T7A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"08:00 am - 09:00 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T8A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T8A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T8A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T8A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T8A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T8A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T8A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T8A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T8A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:00 pm - 08:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T7P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T7P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T7P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T7P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T7P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T7P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T7P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T7P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T7P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"06:00 pm - 07:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 09:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"11:30 am - 12:30 am" ->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"09:15 am - 10:45 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 08:30 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"05:00 pm - 06:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.T5P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.T5P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.T5P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.T5P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.T5P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.T5P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.T5P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.T5P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.T5P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }
                }
                }"W"->{
                when(listaClasesSemana[i].hora){
                    "06:00 am - 07:00 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.W6A.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.W6A.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.W6A.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.W6A.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.W6A.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.W6A.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.W6A.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.W6A.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.W6A.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                    }"07:15 am - 08:15 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W7A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W7A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W7A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W7A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W7A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W7A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W7A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W7A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W7A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"08:00 am - 09:00 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W8A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W8A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W8A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W8A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W8A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W8A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W8A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W8A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W8A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:00 pm - 08:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W7P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W7P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W7P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W7P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W7P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W7P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W7P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W7P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W7P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"06:00 pm - 07:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 09:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"11:30 am - 12:30 am" ->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"09:15 am - 10:45 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 08:30 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"05:00 pm - 06:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.W5P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.W5P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.W5P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.W5P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.W5P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.W5P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.W5P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.W5P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.W5P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }
                }
                }"J"->{
                when(listaClasesSemana[i].hora){
                    "06:00 am - 07:00 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.Th6A.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.Th6A.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.Th6A.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.Th6A.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.Th6A.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.Th6A.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.Th6A.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.Th6A.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.Th6A.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                    }"07:15 am - 08:15 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th7A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th7A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th7A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th7A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th7A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th7A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th7A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th7A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th7A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"08:00 am - 09:00 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th8A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th8A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th8A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th8A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th8A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th8A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th8A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th8A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th7A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:00 pm - 08:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th7P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th7P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th7P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th7P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th7P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th7P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th7P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th7P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th7P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"06:00 pm - 07:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 09:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"11:30 am - 12:30 am" ->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"09:15 am - 10:45 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 08:30 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"05:00 pm - 06:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.Th5P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.Th5P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.Th5P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.Th5P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.Th5P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.Th5P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.Th5P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.Th5P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.Th5P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }
                }
                }"V"->{
                when(listaClasesSemana[i].hora){
                    "06:00 am - 07:00 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.F6A.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.F6A.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.F6A.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.F6A.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.F6A.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.F6A.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.F6A.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.F6A.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.F6A.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                    }"07:15 am - 08:15 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F7A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F7A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F7A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F7A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F7A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F7A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F7A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F7A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F7A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"08:00 am - 09:00 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F8A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F8A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F8A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F8A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F8A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F8A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F8A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F8A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F8A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:00 pm - 08:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F7P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F7P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F7P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F7P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F7P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F7P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F7P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F7P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F7P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"06:00 pm - 07:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 09:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"11:30 am - 12:30 am" ->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"09:15 am - 10:45 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 08:30 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"05:00 pm - 06:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.F5P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.F5P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.F5P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.F5P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.F5P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.F5P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.F5P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.F5P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.F5P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }
                }
                }"S"->{
                when(listaClasesSemana[i].hora){
                    "06:00 am - 07:00 am"->{
                        when(listaClasesSemana[i].nombreClase){
                            "BIKRAM 60 min." ->{
                                binding.S6A.setBackgroundResource(R.drawable.bikram60)
                            }"HOT VINYASA" ->{
                            binding.S6A.setBackgroundResource(R.drawable.hotvinyasac)
                        }"HATHA"->{
                            binding.S6A.setBackgroundResource(R.drawable.hathacuadrito)
                        }"ANTI GRAVITY"->{
                            binding.S6A.setBackgroundResource(R.drawable.antigravityc)
                        }"HOT TRX"->{
                            binding.S6A.setBackgroundResource(R.drawable.hottrxc)
                        }"YOGA WHEEL"->{
                            binding.S6A.setBackgroundResource(R.drawable.yogawheelc)
                        }"YOGA KIDS"->{
                            binding.S6A.setBackgroundResource(R.drawable.yogakidsc)
                        }"BIKRAM 90 min."->{
                            binding.S6A.setBackgroundResource(R.drawable.bikram90)
                        }"POWER WHEEL"->{
                            binding.S6A.setBackgroundResource(R.drawable.power_wheel)
                        }
                        }
                    }"07:15 am - 08:15 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S7A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S7A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S7A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S7A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S7A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S7A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S7A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S7A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S7A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"08:00 am - 09:00 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S8A.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S8A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S8A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S8A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S8A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S8A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S8A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S8A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S8A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:00 pm - 08:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S7P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S7P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S7P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S7P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S7P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S7P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S7P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S7P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S7P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"06:00 pm - 07:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S6P2.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S6P2.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S6P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S6P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S6P2.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S6P2.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S6P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S6P2.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S6P2.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 09:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"11:30 am - 12:30 am" ->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S7P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S7P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S7P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S7P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S7P.setBackgroundResource(R.drawable.hottrx1130)
                    }"YOGA WHEEL"->{
                        binding.S7P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S7P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S7P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S7P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"09:15 am - 10:45 am"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S8A.setBackgroundResource(R.drawable.bikram90915)
                        }"HOT VINYASA" ->{
                        binding.S8A.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S8A.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S8A.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S8A.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S8A.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S8A.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S8A.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S8A.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"07:30 pm - 08:30 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S73P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S73P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S73P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S73P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S73P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S73P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S73P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S73P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S73P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }"05:00 pm - 06:00 pm"->{
                    when(listaClasesSemana[i].nombreClase){
                        "BIKRAM 60 min." ->{
                            binding.S5P.setBackgroundResource(R.drawable.bikram60)
                        }"HOT VINYASA" ->{
                        binding.S5P.setBackgroundResource(R.drawable.hotvinyasac)
                    }"HATHA"->{
                        binding.S5P.setBackgroundResource(R.drawable.hathacuadrito)
                    }"ANTI GRAVITY"->{
                        binding.S5P.setBackgroundResource(R.drawable.antigravityc)
                    }"HOT TRX"->{
                        binding.S5P.setBackgroundResource(R.drawable.hottrxc)
                    }"YOGA WHEEL"->{
                        binding.S5P.setBackgroundResource(R.drawable.yogawheelc)
                    }"YOGA KIDS"->{
                        binding.S5P.setBackgroundResource(R.drawable.yogakidsc)
                    }"BIKRAM 90 min."->{
                        binding.S5P.setBackgroundResource(R.drawable.bikram90)
                    }"POWER WHEEL"->{
                        binding.S5P.setBackgroundResource(R.drawable.power_wheel)
                    }
                    }
                }
                }
                }else->{

                }
            }
        }



        binding.btnBackH.setOnClickListener {
            startActivity(Intent(this, HomeGimnasio::class.java))
        }

        binding.T6A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && listaClasesSemana[i].hora == "06:00 am - 07:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.M6A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && listaClasesSemana[i].hora == "06:00 am - 07:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W6A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && listaClasesSemana[i].hora == "06:00 am - 07:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th6A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && listaClasesSemana[i].hora == "06:00 am - 07:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.F6A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "V" && listaClasesSemana[i].hora == "06:00 am - 07:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.S6A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "06:00 am - 07:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.M7A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && listaClasesSemana[i].hora == "07:15 am - 08:15 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.T7A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && listaClasesSemana[i].hora == "07:15 am - 08:15 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W7A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && listaClasesSemana[i].hora == "07:15 am - 08:15 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th7A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && listaClasesSemana[i].hora == "07:15 am - 08:15 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.F7A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "V" && listaClasesSemana[i].hora == "07:15 am - 08:15 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.S7A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "07:15 am - 08:15 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.M8A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && listaClasesSemana[i].hora == "08:00 am - 09:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.T8A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && listaClasesSemana[i].hora == "08:00 am - 09:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W8A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && listaClasesSemana[i].hora == "08:00 am - 09:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th8A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && listaClasesSemana[i].hora == "08:00 am - 09:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.F8A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "F" && listaClasesSemana[i].hora == "08:00 am - 09:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.S8A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "08:00 am - 09:00 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.M7P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && listaClasesSemana[i].hora == "07:00 pm - 08:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.T7P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && listaClasesSemana[i].hora == "07:00 pm - 08:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W7P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && listaClasesSemana[i].hora == "07:00 pm - 08:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th7P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && listaClasesSemana[i].hora == "07:00 pm - 08:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.F7P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "V" && listaClasesSemana[i].hora == "07:00 pm - 08:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.S7P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "07:00 pm - 08:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.M5P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && listaClasesSemana[i].hora == "05:00 pm - 06:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.T5P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && listaClasesSemana[i].hora == "05:00 pm - 06:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W5P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && listaClasesSemana[i].hora == "05:00 pm - 06:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th5P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && listaClasesSemana[i].hora == "05:00 pm - 06:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.F5P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "F" && listaClasesSemana[i].hora == "05:00 pm - 06:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.S5P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "05:00 pm - 06:00 pm"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }

        binding.M6P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "1"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.T6P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "1"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W6P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "1"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th6P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "1"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.F6P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "V" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "1"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.S6P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "1"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.M6P2.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "2"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.T6P2.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "2"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W6P2.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "2"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th6P2.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "2"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.F6P2.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "V" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "2"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.S6P2.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "06:00 pm - 07:00 pm" && listaClasesSemana[i].SalonId == "2"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.M73P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "L" && (listaClasesSemana[i].hora == "07:30 pm - 08:30 pm" || listaClasesSemana[i].hora == "07:30 pm - 09:00 pm")){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.T73P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "M" && (listaClasesSemana[i].hora == "07:30 pm - 08:30 pm" || listaClasesSemana[i].hora == "07:30 pm - 09:00 pm")){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.W73P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "W" && (listaClasesSemana[i].hora == "07:30 pm - 08:30 pm" || listaClasesSemana[i].hora == "07:30 pm - 09:00 pm")){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.Th73P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "J" && (listaClasesSemana[i].hora == "07:30 pm - 08:30 pm" || listaClasesSemana[i].hora == "07:30 pm - 09:00 pm")){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{
                }
            }
        }
        binding.F73P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "V" && (listaClasesSemana[i].hora == "07:30 pm - 08:30 pm" || listaClasesSemana[i].hora == "07:30 pm - 09:00 pm")){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{
                }
            }
        }
        binding.S73P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && (listaClasesSemana[i].hora == "07:30 pm - 08:30 pm" || listaClasesSemana[i].hora == "07:30 pm - 09:00 pm")){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{
                }
            }
        }
        binding.S8A.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "09:15 am - 10:45 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{
                }
            }
        }
        binding.S7P.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "S" && listaClasesSemana[i].hora == "11:30 am - 12:30 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }
        }
        binding.domingoiv.setOnClickListener {
            for(i in (0 until listaClasesSemana.size)){
                if (listaClasesSemana[i].DOW == "D" && listaClasesSemana[i].hora == "10:00 am - 11:30 am"){
                    val dialog = reservarClaseDialog2(listaClasesSemana[i])
                    dialog.show(supportFragmentManager, "Reservar clase Dialog")
                }
                else{

                }
            }

        }
    }
    private fun llamadaApiC() {
        val job = CoroutineScope(Dispatchers.IO).launch {
            try {
                listaClasesSemana = mutableListOf<clasesSemana>()
                for (i in 1..25) {
                    val url =
                        URL("https://actinseguro.com/booking/abkcom013.aspx?1, $i")
                    val conn = url.openConnection()
                    var datos = StringBuffer()

                    BufferedReader(InputStreamReader(conn.getInputStream())).use { inp ->
                        var line: String?
                        while (inp.readLine().also { line = it } != null) {
                            println(line)
                            datos.append(line)
                        }
                    }
                    val json = datos.toString()
                    val objeto = JSONObject(json)
                    val arrayJson = objeto.getJSONArray("SIGUIENTE_CLASE")
                    val objetoIndice1 = arrayJson.getJSONObject(0)
                    if (objetoIndice1.getString("CIA") != "No Hay Clases para esa fecha")
                        for (i in (0 until arrayJson.length())) {
                            val objeto = arrayJson.getJSONObject(i)
                            val CIA = objeto.getString("CIA")
                            val idClase = objeto.getString("CLASE_ID_CLASE")
                            val nombreClase = objeto.getString("CLASE_NOMBRE")
                            val horario = objeto.getString("CLASE_HORARIO")
                            val fecha = objeto.getString("CLASE_FECHA")
                            val DOW = objeto.getString("CLASE_DOW")
                            val salonId = objeto.getString("CLASE_SALON_ID")
                            val cupo = objeto.getString("CLASE_CUPO_MAX")
                            val ocupado = objeto.getString("CLASE_OCUPADO")
                            listaClasesSemana.add(
                                i,
                                clasesSemana(
                                    i,
                                    CIA,
                                    horario,
                                    DOW,
                                    nombreClase,
                                    idClase,
                                    salonId,
                                    fecha,
                                    cupo,
                                    ocupado
                                )
                            )
                        }
                }
                println(listaClasesSemana)
            } catch (ex: Exception) {
            }
        }
        runBlocking {
            job.join()
        }
    }
}
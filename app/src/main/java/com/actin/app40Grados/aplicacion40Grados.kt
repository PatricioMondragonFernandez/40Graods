package com.actin.app40Grados
import android.app.Application
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.actin.app40Grados.DB.clasesSemanaDB
import com.actin.app40Grados.model.clasesSemana
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

//import com.quickblox.auth.session.QBSettings

//import com.dbflow5.config.FlowManager

private const val APPLICATION_ID = "97855"
private const val AUTH_KEY = "XKhzFHKfgXuGFwM"
private const val AUTH_SECRET = "y6WkqLcau-JhCyH"
private const val ACCOUNT_KEY = "SGz_Zq2-sSstA_farrUC"

class aplicacion40Grados :Application() {

    companion object{
      lateinit var prefs: Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        //LLamada para descargar las clases de la semana


        /*QBSettings.getInstance().init(applicationContext, APPLICATION_ID, AUTH_KEY, AUTH_SECRET)
        QBSettings.getInstance().accountKey = ACCOUNT_KEY*/
    }
}
package com.actin.app40Grados
import android.content.Context

class Prefs(val context: Context) {
    val SHARED_NAME = "Mydtb"
    val SHARED_USERNAME = "username"
    val SHARED_CORREO = "correo"
    val SHARED_CIA = "CIA"
    val SHARED_ID = "ID"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveName(name:String){
        storage.edit().putString(SHARED_USERNAME, name).apply()
    }
    fun saveCorreo(correo : String){
        storage.edit().putString(SHARED_CORREO, correo).apply()
    }
    fun saveCIA(CIA : String){
        storage.edit().putString(SHARED_CIA, CIA).apply()
    }

    fun saveID(ID : String){
        storage.edit().putString(SHARED_ID, ID).apply()
    }

    fun getName():String{
        return storage.getString(SHARED_USERNAME, "")!!
    }

    fun getCorreo(): String{
        return storage.getString(SHARED_CORREO, "")!!
    }

    fun getCIA(): String{
        return  storage.getString(SHARED_CIA, "")!!
    }
    fun getID():String{
        return storage.getString(SHARED_ID,"")!!
    }

    fun wipe(){
        storage.edit().clear().apply()
    }
}
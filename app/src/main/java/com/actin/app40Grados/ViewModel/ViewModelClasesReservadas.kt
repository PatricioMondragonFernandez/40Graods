package com.actin.app40Grados.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.actin.app40Grados.model.clasesReservadas

class ViewModelClasesReservadas : ViewModel() {
    val clasesReservadasModel = MutableLiveData<MutableList<clasesReservadas>>()

    fun addClases(clases: MutableList<clasesReservadas>){
        clasesReservadasModel.postValue(clases)
    }
}
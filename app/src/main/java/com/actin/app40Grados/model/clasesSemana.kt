package com.actin.app40Grados.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class clasesSemana(@PrimaryKey(autoGenerate = true)
                        val id:Int,
                        val CIA: String,
                        val hora: String,
                        val DOW: String,
                        val nombreClase: String,
                        val idClase: String,
                        val SalonId: String,
                        val fecha: String,
                        val cupo: String,
                        val ocupado: String)
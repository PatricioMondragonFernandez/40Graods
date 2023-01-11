package com.actin.app40Grados.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.actin.app40Grados.model.clasesSemana

@Database(
    entities = [clasesSemana::class],
    version = 2
)
abstract class clasesSemanaDB : RoomDatabase(){
    abstract fun clasesSemanaDao() : ClaseSemanaDao
}
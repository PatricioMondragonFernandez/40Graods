package com.actin.app40Grados.DB

import androidx.room.*
import com.actin.app40Grados.model.clasesSemana

@Dao
interface ClaseSemanaDao {
    @Query("SELECT * FROM clasesSemana")
    suspend fun getAll(): MutableList<clasesSemana>

    @Query("SELECT * FROM clasesSemana WHERE id = :id")
    suspend fun getById(id:Int): clasesSemana
    @Update
    suspend fun Update(clasesSemana: clasesSemana)

    @Insert
    suspend fun Insert(clases: List<clasesSemana>)

    @Delete
    suspend fun Delete(clasesSemana: clasesSemana)
}
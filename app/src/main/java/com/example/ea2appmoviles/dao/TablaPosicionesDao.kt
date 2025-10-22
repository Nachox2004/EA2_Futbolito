package com.example.ea2appmoviles.dao

import androidx.room.*
import com.example.ea2appmoviles.model.TablaPosiciones

@Dao
interface TablaPosicionesDao {
    @Query("SELECT * FROM tabla_posiciones ORDER BY posicion ASC")
    suspend fun getAll(): List<TablaPosiciones>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tabla: List<TablaPosiciones>)

    @Update
    suspend fun update(fila: TablaPosiciones)

    @Delete
    suspend fun delete(fila: TablaPosiciones)
}
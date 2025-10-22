package com.example.ea2appmoviles.dao

import androidx.room.*
import com.example.ea2appmoviles.model.Jugador

@Dao
interface JugadorDao {
    @Query("SELECT * FROM jugadores")
    suspend fun getAll(): List<Jugador>

    @Query("SELECT * FROM jugadores WHERE equipoId = :equipoId")
    suspend fun getByEquipo(equipoId: Int): List<Jugador>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jugador: Jugador)

    @Update
    suspend fun update(jugador: Jugador)

    @Delete
    suspend fun delete(jugador: Jugador)
}
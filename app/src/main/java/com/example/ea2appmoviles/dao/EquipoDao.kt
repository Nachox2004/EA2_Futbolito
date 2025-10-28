package com.example.ea2appmoviles.dao

import androidx.room.*
import com.example.ea2appmoviles.model.Equipo

@Dao
interface EquipoDao {
    @Query("SELECT * FROM equipos")
    suspend fun getAll(): List<Equipo>

    @Query("SELECT * FROM equipos WHERE liga = :liga")
    suspend fun getByLiga(liga: String): List<Equipo>

    @Query("SELECT * FROM equipos WHERE id = :id")
    suspend fun getById(id: Int): Equipo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(equipo: Equipo)

    @Update
    suspend fun update(equipo: Equipo)

    @Delete
    suspend fun delete(equipo: Equipo)
}

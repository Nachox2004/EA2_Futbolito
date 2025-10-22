package com.example.ea2appmoviles.dao

import androidx.room.*
import com.example.ea2appmoviles.model.Partido

@Dao
interface PartidoDao {
    @Query("SELECT * FROM partidos")
    suspend fun getAll(): List<Partido>

    @Query("SELECT * FROM partidos WHERE equipoLocalId = :equipoId OR equipoVisitanteId = :equipoId")
    suspend fun getByEquipo(equipoId: Int): List<Partido>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(partido: Partido)

    @Update
    suspend fun update(partido: Partido)

    @Delete
    suspend fun delete(partido: Partido)
}
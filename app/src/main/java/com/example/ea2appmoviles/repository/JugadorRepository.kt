package com.example.ea2appmoviles.repository

import com.example.ea2appmoviles.dao.JugadorDao
import com.example.ea2appmoviles.model.Jugador

class JugadorRepository(private val dao: JugadorDao) {
    suspend fun getAll() = dao.getAll()
    suspend fun getByEquipo(equipoId: Int) = dao.getJugadoresByEquipoId(equipoId)
    suspend fun insert(jugador: Jugador) = dao.insert(jugador)
    suspend fun update(jugador: Jugador) = dao.update(jugador)
    suspend fun delete(jugador: Jugador) = dao.delete(jugador)
}

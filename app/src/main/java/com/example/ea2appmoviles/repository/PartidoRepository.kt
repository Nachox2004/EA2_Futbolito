package com.example.ea2appmoviles.repository

import com.example.ea2appmoviles.dao.PartidoDao
import com.example.ea2appmoviles.model.Partido

class PartidoRepository(private val dao: PartidoDao) {
    suspend fun getAll() = dao.getAll()
    suspend fun getByEquipo(equipoId: Int) = dao.getByEquipo(equipoId)
    suspend fun insert(partido: Partido) = dao.insert(partido)
    suspend fun update(partido: Partido) = dao.update(partido)
    suspend fun delete(partido: Partido) = dao.delete(partido)
}
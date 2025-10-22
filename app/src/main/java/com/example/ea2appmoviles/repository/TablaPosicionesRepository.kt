package com.example.ea2appmoviles.repository

import com.example.ea2appmoviles.dao.TablaPosicionesDao
import com.example.ea2appmoviles.model.TablaPosiciones

class TablaPosicionesRepository(private val dao: TablaPosicionesDao) {
    suspend fun getAll() = dao.getAll()
    suspend fun insertAll(tabla: List<TablaPosiciones>) = dao.insertAll(tabla)
    suspend fun update(fila: TablaPosiciones) = dao.update(fila)
    suspend fun delete(fila: TablaPosiciones) = dao.delete(fila)
}
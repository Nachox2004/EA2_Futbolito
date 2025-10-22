package com.example.ea2appmoviles.repository

import com.example.ea2appmoviles.dao.EquipoDao
import com.example.ea2appmoviles.model.Equipo

class EquipoRepository(private val equipoDao: EquipoDao) {

    suspend fun getAll(): List<Equipo> {
        return equipoDao.getAll()
    }

    suspend fun getByLiga(liga: String): List<Equipo> {
        return equipoDao.getByLiga(liga)
    }

    suspend fun getById(id: Int): Equipo? {
        return equipoDao.getById(id)
    }

    suspend fun insert(equipo: Equipo) {
        equipoDao.insert(equipo)
    }
}

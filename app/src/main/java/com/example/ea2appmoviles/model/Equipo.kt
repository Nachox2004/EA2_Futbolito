package com.example.ea2appmoviles.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipos")
data class Equipo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val escudo: Int,
    val estadio: String,
    val fundacion: String,
    val liga: String
)

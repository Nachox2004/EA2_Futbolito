package com.example.ea2appmoviles.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "jugadores",
    foreignKeys = [ForeignKey(
        entity = Equipo::class,
        parentColumns = ["id"],
        childColumns = ["equipoId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Jugador(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val posicion: String,
    val numero: Int,
    val equipoId: Int // Para saber a qué equipo pertenece
)

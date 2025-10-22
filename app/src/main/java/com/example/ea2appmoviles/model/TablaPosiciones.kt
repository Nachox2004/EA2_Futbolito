package com.example.ea2appmoviles.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tabla_posiciones",
    foreignKeys = [ForeignKey(
        entity = Equipo::class,
        parentColumns = ["id"],
        childColumns = ["equipoId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class TablaPosiciones(
    @PrimaryKey
    val equipoId: Int,
    val posicion: Int,
    val puntos: Int,
    val partidosJugados: Int,
    val victorias: Int,
    val empates: Int,
    val derrotas: Int
)

package com.example.ea2appmoviles.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "partidos",
    foreignKeys = [
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipoLocalId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipoVisitanteId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Partido(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val equipoLocalId: Int,
    val equipoVisitanteId: Int,
    val golesLocal: Int,
    val golesVisitante: Int,
    val fecha: String,
    val estado: String // Ej: "Programado", "En vivo", "Finalizado"
)

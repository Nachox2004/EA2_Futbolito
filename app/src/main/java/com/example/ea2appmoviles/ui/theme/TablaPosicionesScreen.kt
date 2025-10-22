package com.example.ea2appmoviles.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ea2appmoviles.model.Equipo

@Composable
fun TablaPosicionesScreen(equipos: List<Equipo>) {
    LazyColumn {
        items(equipos) { equipo ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = equipo.nombre)
            }
        }
    }
}
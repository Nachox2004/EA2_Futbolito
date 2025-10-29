package com.example.ea2appmoviles.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// 1. Modelo de datos para una noticia
data class Noticia(
    val titular: String,
    val contenido: String
)

// 2. Datos de las noticias
val noticiasPrimeraDivision = listOf(
    Noticia(
        "¡Coquimbo se afianza en la cima!",
        "Con su victoria clave sobre O'Higgins, el equipo pirata se consolida como líder exclusivo del torneo, acercándose al sueño del campeonato."
    ),
    Noticia(
        "Clásico Universitario se tiñe de cruzado",
        "U. Católica venció por la mínima a U. de Chile en un apretado encuentro, logrando tres puntos vitales en la lucha por los puestos de avanzada."
    ),
    Noticia(
        "Colo-Colo y Limache no se sacan ventajas",
        "En un partido de ida y vuelta, albos y tomateros repartieron puntos con un entretenido empate 2-2 que deja a ambos con tareas pendientes."
    )
)

val noticiasPrimeraB = listOf(
    Noticia(
        "¡La cima de la Primera B está al rojo vivo!",
        "Copiapó y Universidad de Concepción igualan en puntaje. La lucha por el ascenso directo se definirá en una infartante última fecha."
    ),
    Noticia(
        "Cobreloa se mete en la pelea por el ascenso",
        "El cuadro loíno venció a Santiago Wanderers en Calama y aseguró su puesto en la liguilla, donde sueña con volver a la división de honor."
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticiasScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Últimas Noticias") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Sección Primera División
            item {
                Text(
                    text = "Primera División",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }
            items(noticiasPrimeraDivision) { noticia ->
                NoticiaCard(noticia = noticia)
            }

            // Sección Primera B
            item {
                Text(
                    text = "Primera B",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                )
            }
            items(noticiasPrimeraB) { noticia ->
                NoticiaCard(noticia = noticia)
            }
        }
    }
}

// 3. Composable para mostrar una noticia
@Composable
fun NoticiaCard(noticia: Noticia) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = noticia.titular,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noticia.contenido,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

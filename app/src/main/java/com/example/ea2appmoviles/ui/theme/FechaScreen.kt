package com.example.ea2appmoviles.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ea2appmoviles.R

// 1. Modelo de datos para un partido
data class Partido(
    val equipoLocalNombre: String,
    val equipoLocalEscudo: Int,
    val equipoLocalGoles: Int,
    val equipoVisitanteNombre: String,
    val equipoVisitanteEscudo: Int,
    val equipoVisitanteGoles: Int
)

// 2. Datos de los partidos
val partidosPrimeraDivision = listOf(
    Partido("Palestino", R.drawable.palestino, 2, "Everton", R.drawable.everton, 1),
    Partido("La Serena", R.drawable.segunda_division, 2, "Audax Italiano", R.drawable.audax_italiano, 1),
    Partido("Huachipato", R.drawable.huachipato, 1, "Iquique", R.drawable.iquique, 1),
    Partido("U. Católica", R.drawable.u_catolica, 1, "U. de Chile", R.drawable.u_de_chile, 0),
    Partido("Cobresal", R.drawable.cobresal, 1, "U. Española", R.drawable.union_espanola, 0),
    Partido("U. La Calera", R.drawable.calera, 3, "Ñublense", R.drawable.nublense, 0),
    Partido("O'Higgins", R.drawable.ohiggins, 0, "Coquimbo Unido", R.drawable.coquimbo_unido, 1),
    Partido("Colo-Colo", R.drawable.colo_colo, 2, "Deportes Limache", R.drawable.limache, 2)
)

val partidosPrimeraB = listOf(
    Partido("San Luis", R.drawable.segunda_division, 0, "Copiapó", R.drawable.copiapo, 0),
    Partido("Univ. Concepción", R.drawable.segunda_division, 3, "Temuco", R.drawable.segunda_division, 2),
    Partido("Unión San Felipe", R.drawable.segunda_division, 2, "Santiago Morning", R.drawable.segunda_division, 0),
    Partido("Santa Cruz", R.drawable.segunda_division, 1, "San Marcos", R.drawable.segunda_division, 0),
    Partido("Recoleta", R.drawable.segunda_division, 1, "Curicó Unido", R.drawable.segunda_division, 1),
    Partido("Magallanes", R.drawable.segunda_division, 1, "Antofagasta", R.drawable.segunda_division, 0),
    Partido("Cobreloa", R.drawable.segunda_division, 2, "Santiago Wanderers", R.drawable.segunda_division, 0),
    Partido("Rangers", R.drawable.segunda_division, 1, "Concepción", R.drawable.segunda_division, 0)
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FechaScreen(navController: NavController, liga: String) {
    val jornada = if (liga == "Primera División") "Jornada 25 de 30" else "Jornada 29 de 30"
    val partidos = if (liga == "Primera División") partidosPrimeraDivision else partidosPrimeraB
    val imagenFondo = if (liga == "Primera División") R.drawable.primera_division else R.drawable.segunda_division

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(jornada) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = imagenFondo),
                contentDescription = "Fondo de la división",
                modifier = Modifier.fillMaxSize().alpha(0.1f),
                contentScale = ContentScale.Crop
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(partidos) { partido ->
                    PartidoCard(partido = partido)
                }
            }
        }
    }
}

// 3. Composable para mostrar un partido
@Composable
fun PartidoCard(partido: Partido) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Equipo Local
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = partido.equipoLocalEscudo), contentDescription = null, modifier = Modifier.size(32.dp))
                    Text(text = partido.equipoLocalNombre, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }

            // Marcador
            Text(
                text = "${partido.equipoLocalGoles} - ${partido.equipoVisitanteGoles}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // Equipo Visitante
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = partido.equipoVisitanteEscudo), contentDescription = null, modifier = Modifier.size(32.dp))
                    Text(text = partido.equipoVisitanteNombre, fontSize = 12.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

package com.example.ea2appmoviles.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ea2appmoviles.model.Equipo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EquipoDetailScreen(
    navController: NavController,
    equipo: Equipo // Parámetros corregidos
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(equipo.nombre) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = equipo.escudo),
                        contentDescription = "Escudo de ${equipo.nombre}",
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = equipo.nombre,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = "Estadio: ${equipo.estadio}", fontSize = 18.sp)
                    Text(text = "Fundado en: ${equipo.fundacion}", fontSize = 16.sp)
                }
            }
        }
    }
}

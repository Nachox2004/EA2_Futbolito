package com.example.ea2appmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ea2appmoviles.database.AppDatabase
import com.example.ea2appmoviles.repository.EquipoRepository
import com.example.ea2appmoviles.ui.theme.EA2AppMovilesTheme
import com.example.ea2appmoviles.ui.theme.EquipoDetailScreen
import com.example.ea2appmoviles.ui.theme.Inicio
import com.example.ea2appmoviles.ui.theme.ListaEquiposScreen
import com.example.ea2appmoviles.ui.theme.NoticiasScreen
import com.example.ea2appmoviles.viewmodel.EquipoViewModel
import com.example.ea2appmoviles.viewmodel.EquipoViewModelFactory

class MainActivity : ComponentActivity() {

    private val database by lazy { AppDatabase.getInstance(this) }
    private val repository by lazy { EquipoRepository(database.equipoDao()) }
    private val viewModelFactory by lazy { EquipoViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EA2AppMovilesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: EquipoViewModel = viewModel(factory = viewModelFactory)
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: EquipoViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            Inicio(navController = navController)
        }

        composable("noticias") {
            NoticiasScreen(navController = navController)
        }

        composable("lista_equipos/{liga}") { backStackEntry ->
            val liga = backStackEntry.arguments?.getString("liga") ?: ""

            LaunchedEffect(liga) {
                viewModel.getEquipos(liga)
            }

            val equipos by viewModel.equipos.collectAsState()

            ListaEquiposScreen(
                navController = navController,
                equipos = equipos
            )
        }

        composable("detalle_equipo/{equipoId}") { backStackEntry ->
            val equipoId = backStackEntry.arguments?.getString("equipoId")?.toIntOrNull()
            LaunchedEffect(equipoId) {
                if (equipoId != null) {
                    viewModel.buscarEquipoPorId(equipoId)
                }
            }
            val equipo by viewModel.equipoSeleccionado.collectAsState()
            equipo?.let {
                EquipoDetailScreen(
                    navController = navController,
                    equipo = it
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EA2AppMovilesTheme {
        val navController = rememberNavController()
        Inicio(navController)
    }
}

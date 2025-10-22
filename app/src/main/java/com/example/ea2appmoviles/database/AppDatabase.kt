package com.example.ea2appmoviles.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ea2appmoviles.R
import com.example.ea2appmoviles.dao.EquipoDao
import com.example.ea2appmoviles.model.Equipo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Equipo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun equipoDao(): EquipoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(DatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    prePopulateDatabase(context, database.equipoDao())
                }
            }
        }

        suspend fun prePopulateDatabase(context: Context, equipoDao: EquipoDao) {
            val todosLosEquipos = listOf(
                // --- PRIMERA DIVISIÓN ---
                Equipo(
                    nombre = "Colo-Colo",
                    escudo = R.drawable.colo_colo,
                    estadio = "Estadio Monumental",
                    fundacion = "1925",
                    liga = "Primera División",
                    noticia1 = "Colo-Colo se mantiene líder del torneo con una goleada.",
                    noticia2 = "Arturo Vidal y Bolados marcaron en la victoria.",
                    ultimoPartidoResultado = "4-0 vs Dep. Iquique",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Universidad de Chile",
                    escudo = R.drawable.u_de_chile,
                    estadio = "Estadio Nacional",
                    fundacion = "1927",
                    liga = "Primera División",
                    noticia1 = "La U empata en el clásico universitario.",
                    noticia2 = "El equipo azul busca asegurar su cupo internacional.",
                    ultimoPartidoResultado = "1-1 vs U. Católica",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "Universidad Católica",
                    escudo = R.drawable.u_catolica,
                    estadio = "Estadio San Carlos de Apoquindo",
                    fundacion = "1937",
                    liga = "Primera División",
                    noticia1 = "Zampedri vuelve al gol en el clásico universitario.",
                    noticia2 = "Católica sigue firme en la parte alta de la tabla.",
                    ultimoPartidoResultado = "1-1 vs U. de Chile",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "Deportes Limache",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Nicolás Chahuán Nazar",
                    fundacion = "2012",
                    liga = "Primera División",
                    noticia1 = "Limache sorprende con gran campaña en su debut en Primera.",
                    noticia2 = "El cuadro tomateño suma su cuarta victoria consecutiva.",
                    ultimoPartidoResultado = "1-0 vs Cobresal",
                    ultimoPartidoFecha = "21/10/2025"
                ),
                Equipo(
                    nombre = "Cobresal",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio El Cobre",
                    fundacion = "1979",
                    liga = "Primera División",
                    noticia1 = "Cobresal cae en su visita a Limache.",
                    noticia2 = "El DT analiza los errores defensivos del equipo.",
                    ultimoPartidoResultado = "0-1 vs Dep. Limache",
                    ultimoPartidoFecha = "21/10/2025"
                ),
                Equipo(
                    nombre = "Palestino",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Municipal de La Cisterna",
                    fundacion = "1920",
                    liga = "Primera División",
                    noticia1 = "Palestino golea y se mete en zona de copas internacionales.",
                    noticia2 = "El delantero Maximiliano Salas sigue imparable.",
                    ultimoPartidoResultado = "3-0 vs Coquimbo Unido",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Unión Española",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Santa Laura",
                    fundacion = "1897",
                    liga = "Primera División",
                    noticia1 = "Unión vence sobre la hora a Audax y se ilusiona con clasificar.",
                    noticia2 = "El equipo hispano celebra 128 años de historia.",
                    ultimoPartidoResultado = "2-1 vs Audax Italiano",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "Audax Italiano",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Bicentenario de La Florida",
                    fundacion = "1910",
                    liga = "Primera División",
                    noticia1 = "Audax no logra sostener la ventaja y cae ante Unión Española.",
                    noticia2 = "El técnico confía en revertir los resultados.",
                    ultimoPartidoResultado = "1-2 vs Unión Española",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "Huachipato",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Huachipato-CAP Acero",
                    fundacion = "1947",
                    liga = "Primera División",
                    noticia1 = "Huachipato rescata un empate ante Everton.",
                    noticia2 = "Los ‘acereros’ se mantienen en zona de copas.",
                    ultimoPartidoResultado = "1-1 vs Everton",
                    ultimoPartidoFecha = "18/10/2025"
                ),
                Equipo(
                    nombre = "Everton",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Sausalito",
                    fundacion = "1909",
                    liga = "Primera División",
                    noticia1 = "Everton suma un punto valioso en Talcahuano.",
                    noticia2 = "El equipo ruletero celebra su aniversario 116.",
                    ultimoPartidoResultado = "1-1 vs Huachipato",
                    ultimoPartidoFecha = "18/10/2025"
                ),
                Equipo(
                    nombre = "O'Higgins",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio El Teniente",
                    fundacion = "1955",
                    liga = "Primera División",
                    noticia1 = "El ‘Capo de Provincia’ no logra mantener la ventaja.",
                    noticia2 = "O’Higgins se prepara para enfrentar a la U.",
                    ultimoPartidoResultado = "1-2 vs Cobresal",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Ñublense",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Nelson Oyarzún Arenas",
                    fundacion = "1916",
                    liga = "Primera División",
                    noticia1 = "Ñublense gana y se aleja del descenso.",
                    noticia2 = "El plantel confía en asegurar la permanencia.",
                    ultimoPartidoResultado = "2-0 vs U. La Calera",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "Coquimbo Unido",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Francisco Sánchez Rumoroso",
                    fundacion = "1958",
                    liga = "Primera División",
                    noticia1 = "Coquimbo no logra sumar en su visita a Palestino.",
                    noticia2 = "La hinchada pide reacción tras tres derrotas consecutivas.",
                    ultimoPartidoResultado = "0-3 vs Palestino",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Deportes Iquique",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Tierra de Campeones",
                    fundacion = "1978",
                    liga = "Primera División",
                    noticia1 = "Iquique cae duramente ante el líder Colo-Colo.",
                    noticia2 = "El DT confía en recuperar puntos en casa.",
                    ultimoPartidoResultado = "0-4 vs Colo-Colo",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Unión La Calera",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Nicolás Chahuán Nazar",
                    fundacion = "1954",
                    liga = "Primera División",
                    noticia1 = "La Calera sigue sin ganar como visitante.",
                    noticia2 = "El club anuncia el regreso de un histórico delantero.",
                    ultimoPartidoResultado = "0-2 vs Ñublense",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "Deportes Copiapó",
                    escudo = R.drawable.primera_division,
                    estadio = "Estadio Luis Valenzuela Hermosilla",
                    fundacion = "1999",
                    liga = "Primera División",
                    noticia1 = "Copiapó pierde nuevamente y se complica con el descenso.",
                    noticia2 = "El DT pide apoyo total para las últimas fechas.",
                    ultimoPartidoResultado = "0-2 vs U. La Calera",
                    ultimoPartidoFecha = "14/10/2025"
                ),

                // --- PRIMERA B ---
                Equipo(
                    nombre = "Cobreloa",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Zorros del Desierto",
                    fundacion = "1977",
                    liga = "Primera B",
                    noticia1 = "Cobreloa cae y se aleja de la zona de ascenso.",
                    noticia2 = "El equipo prepara su último partido como local.",
                    ultimoPartidoResultado = "0-3 vs Santiago Morning",
                    ultimoPartidoFecha = "17/10/2025"
                ),
                Equipo(
                    nombre = "Magallanes",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Municipal de San Bernardo",
                    fundacion = "1897",
                    liga = "Primera B",
                    noticia1 = "Magallanes gana y se ilusiona con volver a Primera.",
                    noticia2 = "Felipe Flores marcó doblete en la victoria.",
                    ultimoPartidoResultado = "3-1 vs Deportes Temuco",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Deportes La Serena",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio La Portada",
                    fundacion = "1955",
                    liga = "Primera B",
                    noticia1 = "La Serena vence a Huachipato en amistoso y mantiene el ritmo.",
                    noticia2 = "Los ‘papayeros’ se consolidan en la parte alta.",
                    ultimoPartidoResultado = "2-1 vs Huachipato",
                    ultimoPartidoFecha = "21/10/2025"
                ),
                Equipo(
                    nombre = "Rangers de Talca",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Fiscal de Talca",
                    fundacion = "1902",
                    liga = "Primera B",
                    noticia1 = "Rangers suma tres puntos valiosos en casa.",
                    noticia2 = "El equipo recupera terreno en la tabla.",
                    ultimoPartidoResultado = "2-0 vs San Marcos de Arica",
                    ultimoPartidoFecha = "18/10/2025"
                ),
                Equipo(
                    nombre = "Santiago Wanderers",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Elías Figueroa Brander",
                    fundacion = "1892",
                    liga = "Primera B",
                    noticia1 = "Wanderers gana y se acerca a los puestos de ascenso.",
                    noticia2 = "El DT destaca la entrega de sus jugadores.",
                    ultimoPartidoResultado = "1-0 vs San Luis de Quillota",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "San Luis de Quillota",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Lucio Fariña Fernández",
                    fundacion = "1919",
                    liga = "Primera B",
                    noticia1 = "San Luis pierde en un duelo parejo ante Wanderers.",
                    noticia2 = "El club ya piensa en la próxima temporada.",
                    ultimoPartidoResultado = "0-1 vs S. Wanderers",
                    ultimoPartidoFecha = "20/10/2025"
                ),
                Equipo(
                    nombre = "Deportes Temuco",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Germán Becker",
                    fundacion = "1960",
                    liga = "Primera B",
                    noticia1 = "Temuco cae ante Magallanes y pierde terreno.",
                    noticia2 = "El club analiza cambios para el cierre del torneo.",
                    ultimoPartidoResultado = "1-3 vs Magallanes",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Santiago Morning",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Municipal de La Pintana",
                    fundacion = "1903",
                    liga = "Primera B",
                    noticia1 = "El ‘Chago’ golea a Cobreloa y sueña con el ascenso.",
                    noticia2 = "Su delantero es el máximo goleador del torneo.",
                    ultimoPartidoResultado = "3-0 vs Cobreloa",
                    ultimoPartidoFecha = "17/10/2025"
                ),
                Equipo(
                    nombre = "Deportes Recoleta",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio Municipal de Recoleta",
                    fundacion = "2014",
                    liga = "Primera B",
                    noticia1 = "Recoleta empata y se mantiene fuera del descenso.",
                    noticia2 = "El club organiza jornada solidaria en su comuna.",
                    ultimoPartidoResultado = "1-1 vs Curicó Unido",
                    ultimoPartidoFecha = "19/10/2025"
                ),
                Equipo(
                    nombre = "Curicó Unido",
                    escudo = R.drawable.segunda_division,
                    estadio = "Estadio La Granja",
                    fundacion = "1973",
                    liga = "Primera B",
                    noticia1 = "Curicó rescata un empate ante Recoleta.",
                    noticia2 = "El DT destacó la actitud del plantel.",
                    ultimoPartidoResultado = "1-1 vs Recoleta",
                    ultimoPartidoFecha = "19/10/2025"
                )
            )

            todosLosEquipos.forEach { equipo ->
                equipoDao.insert(equipo)
            }
        }


    }
}

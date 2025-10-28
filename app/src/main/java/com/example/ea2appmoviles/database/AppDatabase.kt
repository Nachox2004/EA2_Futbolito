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

@Database(entities = [Equipo::class], version = 4, exportSchema = false)
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
                    .fallbackToDestructiveMigration()
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
                Equipo(nombre = "Colo-Colo", escudo = R.drawable.colo_colo, estadio = "Estadio Monumental", fundacion = "1925", liga = "Primera División"),
                Equipo(nombre = "Universidad de Chile", escudo = R.drawable.u_de_chile, estadio = "Estadio Nacional", fundacion = "1927", liga = "Primera División"),
                Equipo(nombre = "Universidad Católica", escudo = R.drawable.u_catolica, estadio = "Estadio San Carlos de Apoquindo", fundacion = "1937", liga = "Primera División"),
                Equipo(nombre = "Deportes Limache", escudo = R.drawable.limache, estadio = "Estadio Nicolás Chahuán Nazar", fundacion = "2012", liga = "Primera División"),
                Equipo(nombre = "Cobresal", escudo = R.drawable.cobresal, estadio = "Estadio El Cobre", fundacion = "1979", liga = "Primera División"),
                Equipo(nombre = "Palestino", escudo = R.drawable.palestino, estadio = "Estadio Municipal de La Cisterna", fundacion = "1920", liga = "Primera División"),
                Equipo(nombre = "Unión Española", escudo = R.drawable.union_espanola, estadio = "Estadio Santa Laura", fundacion = "1897", liga = "Primera División"),
                Equipo(nombre = "Audax Italiano", escudo = R.drawable.audax_italiano, estadio = "Estadio Bicentenario de La Florida", fundacion = "1910", liga = "Primera División"),
                Equipo(nombre = "Huachipato", escudo = R.drawable.huachipato, estadio = "Estadio Huachipato-CAP Acero", fundacion = "1947", liga = "Primera División"),
                Equipo(nombre = "Everton", escudo = R.drawable.everton, estadio = "Estadio Sausalito", fundacion = "1909", liga = "Primera División"),
                Equipo(nombre = "O'Higgins", escudo = R.drawable.ohiggins, estadio = "Estadio El Teniente", fundacion = "1955", liga = "Primera División"),
                Equipo(nombre = "Ñublense", escudo = R.drawable.nublense, estadio = "Estadio Nelson Oyarzún Arenas", fundacion = "1916", liga = "Primera División"),
                Equipo(nombre = "Coquimbo Unido", escudo = R.drawable.coquimbo_unido, estadio = "Estadio Francisco Sánchez Rumoroso", fundacion = "1958", liga = "Primera División"),
                Equipo(nombre = "Deportes Iquique", escudo = R.drawable.iquique, estadio = "Estadio Tierra de Campeones", fundacion = "1978", liga = "Primera División"),
                Equipo(nombre = "Unión La Calera", escudo = R.drawable.calera, estadio = "Estadio Nicolás Chahuán Nazar", fundacion = "1954", liga = "Primera División"),
                Equipo(nombre = "Deportes Copiapó", escudo = R.drawable.copiapo, estadio = "Estadio Luis Valenzuela Hermosilla", fundacion = "1999", liga = "Primera División"),

                // --- PRIMERA B ---
                Equipo(nombre = "Cobreloa", escudo = R.drawable.segunda_division, estadio = "Estadio Zorros del Desierto", fundacion = "1977", liga = "Primera B"),
                Equipo(nombre = "Magallanes", escudo = R.drawable.segunda_division, estadio = "Estadio Municipal de San Bernardo", fundacion = "1897", liga = "Primera B"),
                Equipo(nombre = "Deportes La Serena", escudo = R.drawable.segunda_division, estadio = "Estadio La Portada", fundacion = "1955", liga = "Primera B"),
                Equipo(nombre = "Rangers de Talca", escudo = R.drawable.segunda_division, estadio = "Estadio Fiscal de Talca", fundacion = "1902", liga = "Primera B"),
                Equipo(nombre = "Santiago Wanderers", escudo = R.drawable.segunda_division, estadio = "Estadio Elías Figueroa Brander", fundacion = "1892", liga = "Primera B"),
                Equipo(nombre = "San Luis de Quillota", escudo = R.drawable.segunda_division, estadio = "Estadio Lucio Fariña Fernández", fundacion = "1919", liga = "Primera B"),
                Equipo(nombre = "Deportes Temuco", escudo = R.drawable.segunda_division, estadio = "Estadio Germán Becker", fundacion = "1960", liga = "Primera B"),
                Equipo(nombre = "Santiago Morning", escudo = R.drawable.segunda_division, estadio = "Estadio Municipal de La Pintana", fundacion = "1903", liga = "Primera B"),
                Equipo(nombre = "Deportes Recoleta", escudo = R.drawable.segunda_division, estadio = "Estadio Municipal de Recoleta", fundacion = "2014", liga = "Primera B"),
                Equipo(nombre = "Curicó Unido", escudo = R.drawable.segunda_division, estadio = "Estadio La Granja", fundacion = "1973", liga = "Primera B")
            )

            todosLosEquipos.forEach { equipo ->
                equipoDao.insert(equipo)
            }
        }
    }
}

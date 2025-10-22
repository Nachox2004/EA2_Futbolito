package com.example.ea2appmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ea2appmoviles.model.Equipo
import com.example.ea2appmoviles.repository.EquipoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EquipoViewModel(private val repository: EquipoRepository) : ViewModel() {

    private val _equipos = MutableStateFlow<List<Equipo>>(emptyList())
    val equipos: StateFlow<List<Equipo>> = _equipos

    private val _equipoSeleccionado = MutableStateFlow<Equipo?>(null)
    val equipoSeleccionado: StateFlow<Equipo?> = _equipoSeleccionado

    fun getEquipos(liga: String) {
        viewModelScope.launch {
            _equipos.value = repository.getByLiga(liga)
        }
    }

    fun buscarEquipoPorId(id: Int) {
        viewModelScope.launch {
            _equipoSeleccionado.value = repository.getById(id)
        }
    }
}


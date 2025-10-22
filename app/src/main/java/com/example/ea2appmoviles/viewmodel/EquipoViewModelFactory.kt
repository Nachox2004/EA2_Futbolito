package com.example.ea2appmoviles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ea2appmoviles.repository.EquipoRepository

class EquipoViewModelFactory(private val repository: EquipoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EquipoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EquipoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
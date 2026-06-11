package com.jorge.animeboxd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jorge.animeboxd.data.repository.AnimeRepository
import com.jorge.animeboxd.presentation.catalogo.CatalogoViewModel
import com.jorge.animeboxd.presentation.minhaLista.MinhaListaViewModel

class ViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MinhaListaViewModel::class.java)  -> MinhaListaViewModel(repository) as T
            modelClass.isAssignableFrom(CatalogoViewModel::class.java) -> CatalogoViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
        }
    }
}
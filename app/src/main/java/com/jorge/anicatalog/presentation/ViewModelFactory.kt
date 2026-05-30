package com.jorge.anicatalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jorge.anicatalog.data.repository.AnimeRepository

class ViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(com.jorge.anicatalog.presentation.mylist.MyListViewModel::class.java) ->
                com.jorge.anicatalog.presentation.mylist.MyListViewModel(repository) as T
            modelClass.isAssignableFrom(com.jorge.anicatalog.presentation.catalog.CatalogViewModel::class.java) ->
                com.jorge.anicatalog.presentation.catalog.CatalogViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel desconhecido")
        }
    }
}
package com.jorge.anicatalog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jorge.anicatalog.data.repository.AnimeRepository
import com.jorge.anicatalog.presentation.catalog.CatalogViewModel
import com.jorge.anicatalog.presentation.mylist.MyListViewModel

class ViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MyListViewModel::class.java)  -> MyListViewModel(repository) as T
            modelClass.isAssignableFrom(CatalogViewModel::class.java) -> CatalogViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
        }
    }
}
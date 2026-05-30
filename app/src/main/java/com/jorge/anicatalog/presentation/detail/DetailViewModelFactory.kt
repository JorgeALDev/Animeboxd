package com.jorge.anicatalog.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jorge.anicatalog.data.repository.AnimeRepository

class DetailViewModelFactory(
    private val animeId: Int,
    private val repository: AnimeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(repository, animeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
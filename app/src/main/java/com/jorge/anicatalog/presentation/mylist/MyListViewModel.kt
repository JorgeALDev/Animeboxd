package com.jorge.anicatalog.presentation.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.anicatalog.data.local.AnimeEntity
import com.jorge.anicatalog.data.repository.AnimeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyListViewModel(private val repository: AnimeRepository) : ViewModel() {

    val watchedAnimes: StateFlow<List<AnimeEntity>> = repository
        .getWatchedAnimes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun removeAnime(anime: AnimeEntity) {
        viewModelScope.launch {
            repository.removeAnime(anime)
        }
    }

    fun updateRating(anime: AnimeEntity, newRating: Float) {
        viewModelScope.launch {
            repository.updateAnime(anime.copy(userRating = newRating))
        }
    }

    fun updateStatus(anime: AnimeEntity, newStatus: String) {
        viewModelScope.launch {
            repository.updateAnime(anime.copy(status = newStatus))
        }
    }

    fun getTotalHours(): Int {
        val animes = watchedAnimes.value
        val totalMin = animes.sumOf { it.episodes * it.episodeDurationMin }
        return totalMin / 60
    }
}
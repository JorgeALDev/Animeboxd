package com.jorge.animeboxd.presentation.mylist

import com.jorge.animeboxd.data.local.AnimeEntity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.animeboxd.data.repository.AnimeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyListViewModel(private val repository: AnimeRepository) : ViewModel() {

    val watchedAnimes: StateFlow<List<AnimeEntity>> = repository
        .getWatchedAnimes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun removeAnime(anime: AnimeEntity) {
        viewModelScope.launch { repository.removeAnime(anime) }
    }

    fun updateStatus(anime: AnimeEntity, newStatus: String) {
        viewModelScope.launch { repository.updateAnime(anime.copy(status = newStatus)) }
    }

    fun updateRating(anime: AnimeEntity, newRating: Float) {
        viewModelScope.launch { repository.updateAnime(anime.copy(rating = newRating)) }
    }

    fun updateWatchedEpisodes(anime: AnimeEntity, newWatched: Int) {
        val clamped = newWatched.coerceIn(0, anime.episodes)
        viewModelScope.launch { repository.updateAnime(anime.copy(watchedEpisodes = clamped)) }
    }

    fun getTotalHours(): Int {
        val totalMin = watchedAnimes.value.sumOf { it.episodes * it.episodeDurationMin }
        return totalMin / 60
    }

    fun getTotalMinutesRemainder(): Int {
        val totalMin = watchedAnimes.value.sumOf { it.episodes * it.episodeDurationMin }
        return totalMin % 60
    }
}
package com.jorge.anicatalog.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.anicatalog.data.local.AnimeEntity
import com.jorge.anicatalog.data.repository.AnimeRepository
import com.jorge.anicatalog.domain.model.Anime
import com.jorge.anicatalog.domain.model.animes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: AnimeRepository,
    private val animeId: Int
) : ViewModel() {

    private val _anime = MutableStateFlow<Anime?>(null)
    val anime: StateFlow<Anime?> = _anime

    private val _myListEntry = MutableStateFlow<AnimeEntity?>(null)
    val myListEntry: StateFlow<AnimeEntity?> = _myListEntry

    val isInMyList: StateFlow<Boolean> = _myListEntry
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val userRating: StateFlow<Float> = _myListEntry
        .map { it?.userRating ?: 0f }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0f)

    val userStatus: StateFlow<String> = _myListEntry
        .map { it?.status ?: "" }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    init {
        loadAnimeAndListEntry()
    }

    private fun loadAnimeAndListEntry() {
        viewModelScope.launch {
            val catalogAnime = animes.find { it.id == animeId }
            _anime.value = catalogAnime
            repository.getWatchedAnimes().collect { allWatched ->
                val entry = allWatched.find { it.id == animeId }
                _myListEntry.value = entry
            }
        }
    }

    fun addToMyList() {
        val anime = _anime.value ?: return
        viewModelScope.launch {
            val entity = AnimeEntity(
                id = anime.id,
                title = anime.titulo,
                genre = anime.genero,
                episodes = anime.episodios,
                episodeDurationMin = anime.duracaoEpMin,
                imageUrl = anime.imagemUrl,
                userRating = 0f,
                status = "WATCHING"
            )
            repository.addAnime(entity)
        }
    }

    fun updateRating(rating: Float) {
        val currentEntry = _myListEntry.value ?: return
        viewModelScope.launch {
            repository.updateAnime(currentEntry.copy(userRating = rating))
        }
    }

    fun updateStatus(status: String) {
        val currentEntry = _myListEntry.value ?: return
        viewModelScope.launch {
            repository.updateAnime(currentEntry.copy(status = status))
        }
    }

    fun removeFromMyList() {
        val currentEntry = _myListEntry.value ?: return
        viewModelScope.launch {
            repository.removeAnime(currentEntry)
        }
    }
}
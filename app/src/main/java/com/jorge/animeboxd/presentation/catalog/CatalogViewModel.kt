package com.jorge.animeboxd.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.animeboxd.data.local.AnimeEntity
import com.jorge.animeboxd.data.repository.AnimeRepository
import com.jorge.animeboxd.domain.model.Anime
import com.jorge.animeboxd.domain.model.animes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class CatalogViewModel(private val repository: AnimeRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    val filteredAnimes: List<Anime>
        get() = if (_searchQuery.value.isBlank()) animes
        else animes.filter {
            it.titulo.contains(_searchQuery.value, ignoreCase = true) ||
                    it.genero.contains(_searchQuery.value, ignoreCase = true)
        }

    val savedIds: StateFlow<Set<Int>> = repository.getWatchedAnimes()
        .map<List<AnimeEntity>, Set<Int>> { list -> list.map { it.id }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.Eagerly,
            initialValue = emptySet()
        )

    init {
        viewModelScope.launch {
            delay(500)
            _isLoading.value = false
        }
    }

    fun onSearchChange(query: String) {
        _searchQuery.value = query
    }

    fun addToMyList(anime: Anime) {
        viewModelScope.launch {
            repository.addAnime(
                AnimeEntity(
                    id = anime.id,
                    title = anime.titulo,
                    genre = anime.genero,
                    imageUrl = anime.imagemUrl,
                    episodes = anime.episodios,
                    episodeDurationMin = anime.duracaoEpMin,
                    status = "WATCHING"
                )
            )
        }
    }

    fun removeFromMyList(anime: Anime) {
        viewModelScope.launch {
            val entity = AnimeEntity(
                id = anime.id,
                title = anime.titulo,
                genre = anime.genero,
                imageUrl = anime.imagemUrl,
                episodes = anime.episodios,
                episodeDurationMin = anime.duracaoEpMin,
                status = ""
            )
            repository.removeAnime(entity)
        }
    }
}
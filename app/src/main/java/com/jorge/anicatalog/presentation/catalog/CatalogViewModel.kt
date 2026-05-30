package com.jorge.anicatalog.presentation.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.anicatalog.data.local.AnimeEntity
import com.jorge.anicatalog.data.repository.AnimeRepository
import com.jorge.anicatalog.domain.model.Anime
import com.jorge.anicatalog.domain.model.animes
import kotlinx.coroutines.launch

class CatalogViewModel(private val repository: AnimeRepository) : ViewModel() {

    val catalogAnimes: List<Anime> = animes

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
                    userRating = anime.nota,
                    status = "COMPLETED"
                )
            )
        }
    }
}
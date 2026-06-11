package com.jorge.animeboxd.presentation.catalogo

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

class CatalogoViewModel(private val repositorio: AnimeRepository) : ViewModel() {

    private val _busca = MutableStateFlow("")
    val busca: StateFlow<String> = _busca.asStateFlow()

    private val _estaCarregando = MutableStateFlow(true)
    val estaCarregando: StateFlow<Boolean> = _estaCarregando.asStateFlow()

    val animesFiltrados: List<Anime>
        get() = if (_busca.value.isBlank()) animes
        else animes.filter {
            it.titulo.contains(_busca.value, ignoreCase = true) ||
                    it.genero.contains(_busca.value, ignoreCase = true)
        }

    val idsSalvos: StateFlow<Set<Int>> = repositorio.getWatchedAnimes()
        .map<List<AnimeEntity>, Set<Int>> { lista -> lista.map { it.id }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.Eagerly,
            initialValue = emptySet()
        )

    init {
        viewModelScope.launch {
            delay(500)
            _estaCarregando.value = false
        }
    }

    fun aoMudarBusca(texto: String) {
        _busca.value = texto
    }

    fun adicionarNaLista(anime: Anime) {
        viewModelScope.launch {
            repositorio.addAnime(
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

    fun removerDaLista(anime: Anime) {
        viewModelScope.launch {
            val entidade = AnimeEntity(
                id = anime.id,
                title = anime.titulo,
                genre = anime.genero,
                imageUrl = anime.imagemUrl,
                episodes = anime.episodios,
                episodeDurationMin = anime.duracaoEpMin,
                status = ""
            )
            repositorio.removeAnime(entidade)
        }
    }
}
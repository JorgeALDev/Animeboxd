package com.jorge.animeboxd.presentation.minhaLista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.animeboxd.data.local.AnimeEntity
import com.jorge.animeboxd.data.repository.AnimeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MinhaListaViewModel(private val repositorio: AnimeRepository) : ViewModel() {

    val animesSalvos: StateFlow<List<AnimeEntity>> = repositorio
        .getWatchedAnimes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun removerAnime(anime: AnimeEntity) {
        viewModelScope.launch { repositorio.removeAnime(anime) }
    }

    fun atualizarStatus(anime: AnimeEntity, novoStatus: String) {
        viewModelScope.launch { repositorio.updateAnime(anime.copy(status = novoStatus)) }
    }

    fun obterTotalHoras(): Int {
        val totalMinutos = animesSalvos.value
            .filter { it.status.uppercase() == "COMPLETED" }
            .sumOf { it.episodes * it.episodeDurationMin }
        return totalMinutos / 60
    }

    fun obterMinutosRestantes(): Int {
        val totalMinutos = animesSalvos.value
            .filter { it.status.uppercase() == "COMPLETED" }
            .sumOf { it.episodes * it.episodeDurationMin }
        return totalMinutos % 60
    }
}
package com.jorge.animeboxd.presentation.catalogo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorge.animeboxd.data.repository.AnimeRepository
import com.jorge.animeboxd.domain.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogoViewModel(private val repositorio: AnimeRepository) : ViewModel() {

    private val _busca = MutableStateFlow("")
    val busca: StateFlow<String> = _busca.asStateFlow()

    private val _estaCarregando = MutableStateFlow(true)
    val estaCarregando: StateFlow<Boolean> = _estaCarregando.asStateFlow()

    private val _paginaAtual = MutableStateFlow(1)
    val paginaAtual: StateFlow<Int> = _paginaAtual.asStateFlow()

    private val _totalPaginas = MutableStateFlow(1)
    val totalPaginas: StateFlow<Int> = _totalPaginas.asStateFlow()

    private val _limitePopulares = 10
    private val _limiteBusca = 10

    private val _animes = MutableStateFlow<List<Anime>>(emptyList())
    private val _erro = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _erro.asStateFlow()

    val animesFiltrados: List<Anime>
        get() = if (_busca.value.isBlank()) {
            _animes.value
        } else {
            _animes.value.filter {
                it.titulo.contains(_busca.value, ignoreCase = true) ||
                        it.genero.contains(_busca.value, ignoreCase = true)
            }
        }

    private val _idsSalvos = MutableStateFlow<Set<Int>>(emptySet())
    val idsSalvos: StateFlow<Set<Int>> = _idsSalvos

    init {
        carregarAnimesPopulares()
        viewModelScope.launch {
            repositorio.getWatchedAnimes().collect { lista ->
                _idsSalvos.value = lista.map { it.id }.toSet()
            }
        }
    }

    private fun carregarAnimesPopulares() {
        viewModelScope.launch {
            _estaCarregando.value = true
            _erro.value = null
            try {
                val lista = repositorio.buscarAnimesPopulares(limite = 10)
                _animes.value = lista.distinctBy { it.id }
            } catch (e: Exception) {
                _erro.value = "Erro ao carregar: ${e.message}"
            } finally {
                _estaCarregando.value = false
            }
        }
    }

    fun aoMudarBusca(texto: String) {
        _busca.value = texto
        if (texto.isBlank()) {
            carregarAnimesPopulares(1)
        } else {
            buscarAnimes(texto, 1)
        }
    }

    fun adicionarNaLista(anime: Anime) {
        viewModelScope.launch {
            repositorio.addAnime(
                com.jorge.animeboxd.data.local.AnimeEntity(
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
            val entidade = com.jorge.animeboxd.data.local.AnimeEntity(
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

    private fun carregarAnimesPopulares(pagina: Int = 1) {
        viewModelScope.launch {
            _estaCarregando.value = true
            _erro.value = null
            try {
                val lista =
                    repositorio.buscarAnimesPopulares(limite = _limitePopulares, pagina = pagina)
                _animes.value = lista.distinctBy { it.id }
                _paginaAtual.value = pagina
                _totalPaginas.value = 100
            } catch (e: Exception) {
                _erro.value = "Erro ao carregar: ${e.message}"
            } finally {
                _estaCarregando.value = false
            }
        }
    }

    private fun buscarAnimes(termo: String, pagina: Int = 1) {
        viewModelScope.launch {
            _estaCarregando.value = true
            _erro.value = null
            try {
                val lista =
                    repositorio.buscarAnimesPorNome(termo, limite = _limiteBusca, pagina = pagina)
                _animes.value = lista.distinctBy { it.id }
                _paginaAtual.value = pagina
                _totalPaginas.value = 100
            } catch (e: Exception) {
                _erro.value = "Erro ao buscar: ${e.message}"
            } finally {
                _estaCarregando.value = false
            }
        }
    }

    fun irParaPagina(pagina: Int) {
        if (pagina < 1) return
        viewModelScope.launch {
            if (_busca.value.isBlank()) {
                carregarAnimesPopulares(pagina)
            } else {
                buscarAnimes(_busca.value, pagina)
            }
        }
    }
}
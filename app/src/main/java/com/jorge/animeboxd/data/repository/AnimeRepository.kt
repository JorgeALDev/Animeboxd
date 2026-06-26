package com.jorge.animeboxd.data.repository

import com.jorge.animeboxd.data.local.AnimeDao
import com.jorge.animeboxd.data.local.AnimeEntity
import com.jorge.animeboxd.data.remote.RetrofitClient
import com.jorge.animeboxd.data.remote.model.toAnime
import com.jorge.animeboxd.domain.model.Anime
import kotlinx.coroutines.flow.Flow

class AnimeRepository(private val dao: AnimeDao) {

    // ---- Banco de dados local ----
    fun getWatchedAnimes(): Flow<List<AnimeEntity>> = dao.getAllWatched()

    suspend fun addAnime(anime: AnimeEntity) = dao.insert(anime)

    suspend fun updateAnime(anime: AnimeEntity) = dao.update(anime)

    suspend fun removeAnime(anime: AnimeEntity) = dao.delete(anime)

    // ---- API Jikan ----
    suspend fun buscarAnimesPopulares(limite: Int = 10, pagina: Int = 1): List<Anime> {
        val response = RetrofitClient.createJikanApi().getTopAnimes(limit = limite, page = pagina)
        return response.data?.map { it.toAnime() } ?: emptyList()
    }

    suspend fun buscarAnimesPorNome(consulta: String, limite: Int = 25, pagina: Int = 1): List<Anime> {
        val response = RetrofitClient.createJikanApi().searchAnimes(query = consulta, limit = limite, page = pagina)
        return response.data?.map { it.toAnime() } ?: emptyList()
    }
}
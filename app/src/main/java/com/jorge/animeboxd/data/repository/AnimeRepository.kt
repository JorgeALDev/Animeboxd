package com.jorge.animeboxd.data.repository

import com.jorge.animeboxd.data.local.AnimeDao
import com.jorge.animeboxd.data.local.AnimeEntity
import kotlinx.coroutines.flow.Flow

class AnimeRepository(private val dao: AnimeDao) {

    fun getWatchedAnimes(): Flow<List<AnimeEntity>> = dao.getAllWatched()

    suspend fun addAnime(anime: AnimeEntity) = dao.insert(anime)

    suspend fun updateAnime(anime: AnimeEntity) = dao.update(anime)

    suspend fun removeAnime(anime: AnimeEntity) = dao.delete(anime)
}
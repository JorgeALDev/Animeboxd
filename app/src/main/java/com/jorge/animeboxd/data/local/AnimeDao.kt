package com.jorge.animeboxd.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM watched_animes ORDER BY addedAt DESC")
    fun getAllWatched(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity): Long

    @Update
    suspend fun update(anime: AnimeEntity): Int

    @Delete
    suspend fun delete(anime: AnimeEntity): Int
}
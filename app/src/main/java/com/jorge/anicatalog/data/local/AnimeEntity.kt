package com.jorge.anicatalog.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watched_animes")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val genre: String,
    val imageUrl: String,
    val episodes: Int,
    val episodeDurationMin: Int,
    val status: String,
    val watchedEpisodes: Int = 0,
    val rating: Float = 0f,
    val addedAt: Long = System.currentTimeMillis()
)
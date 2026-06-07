package com.jorge.animeboxd.data.local

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
    val addedAt: Long = System.currentTimeMillis()
)
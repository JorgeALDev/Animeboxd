package com.jorge.animeboxd.domain.model

data class Anime(
    val id: Int,
    val titulo: String,
    val genero: String,
    val sinopse: String,
    val imagemUrl: String,
    val episodios: Int,
    val duracaoEpMin: Int
)

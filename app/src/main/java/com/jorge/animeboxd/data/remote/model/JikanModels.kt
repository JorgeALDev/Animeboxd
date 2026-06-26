package com.jorge.animeboxd.data.remote.model

import com.google.gson.annotations.SerializedName

data class JikanResponse(
    @SerializedName("data") val data: List<AnimeData>? = null,
    @SerializedName("pagination") val pagination: Pagination? = null
)

data class AnimeData(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val titulo: String,
    @SerializedName("synopsis") val sinopse: String? = null,
    @SerializedName("episodes") val episodios: Int? = 0,
    @SerializedName("duration") val duracao: String? = null,
    @SerializedName("images") val imagens: Imagens? = null,
    @SerializedName("genres") val generos: List<Genero>? = null,
    @SerializedName("status") val status: String? = null
)

data class Imagens(
    @SerializedName("jpg") val jpg: Jpg? = null
)

data class Jpg(
    @SerializedName("image_url") val imageUrl: String? = null
)

data class Genero(
    @SerializedName("name") val nome: String? = null
)

data class Pagination(
    @SerializedName("last_visible_page") val ultimaPaginaVisivel: Int = 1,
    @SerializedName("has_next_page") val temProximaPagina: Boolean = false,
    @SerializedName("current_page") val paginaAtual: Int = 1
)

fun AnimeData.toAnime(): com.jorge.animeboxd.domain.model.Anime {
    val duracaoMin = extrairMinutos(duracao ?: "")
    return com.jorge.animeboxd.domain.model.Anime(
        id = id,
        titulo = titulo,
        genero = generos?.joinToString(" / ") { it.nome ?: "Desconhecido" } ?: "Desconhecido",
        sinopse = sinopse ?: "Sinopse não disponível",
        imagemUrl = imagens?.jpg?.imageUrl ?: "",
        episodios = episodios ?: 0,
        duracaoEpMin = duracaoMin
    )
}

fun extrairMinutos(duration: String): Int {
    val numeros = duration.replace("\\D+".toRegex(), "").toIntOrNull()
    return numeros ?: 0
}
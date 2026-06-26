package com.jorge.animeboxd.data.remote.api

import com.jorge.animeboxd.data.remote.model.JikanResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JikanApi {
    @GET("top/anime")
    suspend fun getTopAnimes(
        @Query("limit") limit: Int = 10,
        @Query("page") page: Int = 1
    ): JikanResponse

    @GET("anime")
    suspend fun searchAnimes(
        @Query("q") query: String,
        @Query("limit") limit: Int = 25,
        @Query("page") page: Int = 1
    ): JikanResponse
}
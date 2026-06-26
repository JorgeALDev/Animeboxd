package com.jorge.animeboxd.data.remote

import com.jorge.animeboxd.data.remote.api.JikanApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {

    companion object {
        private const val BASE_URL = "https://api.jikan.moe/v4/"

        private lateinit var instance: Retrofit

        fun getRetrofitInstance(): Retrofit {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    val loggingInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }

                    val httpClient = OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build()

                    instance = Retrofit.Builder()
                        .client(httpClient)
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return instance
            }
        }

        fun createJikanApi(): JikanApi {
            return getRetrofitInstance().create(JikanApi::class.java)
        }
    }
}
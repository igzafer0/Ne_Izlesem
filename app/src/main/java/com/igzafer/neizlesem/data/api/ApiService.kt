package com.igzafer.neizlesem.data.api

import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.data.model.BaseMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int
    ): Response<BaseMovieModel>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int,
        @Query("region") region: String = BuildConfig.REGION,
        ): Response<BaseMovieModel>
}
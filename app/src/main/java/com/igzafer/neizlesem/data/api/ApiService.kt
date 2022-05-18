package com.igzafer.neizlesem.data.api

import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.data.model.actor.BaseActorModel
import com.igzafer.neizlesem.data.model.category.BaseCategoryModel
import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int
    ): Response<BaseMovieModel>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int,
        @Query("region") region: String = BuildConfig.REGION,
    ): Response<BaseMovieModel>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int,
        @Query("region") region: String = BuildConfig.REGION,
    ): Response<BaseMovieModel>

    @GET("trending/movie/week")
    suspend fun getTrendingWeeklyMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("media_type") type: String = "movie"
    ): Response<BaseMovieModel>

    @GET("person/popular")
    suspend fun getPopularActors(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int,
        @Query("region") region: String = BuildConfig.REGION,
    ): Response<BaseActorModel>

    @GET("genre/movie/list")
    suspend fun getMoviesGenre(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): Response<BaseCategoryModel>

    @GET("genre/tv/list")
    suspend fun getTvShowsGenre(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): Response<BaseCategoryModel>

    @GET("discover/movie")
    suspend fun discoverMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int,
        @Query("with_genres") genres: Int

    ): Response<BaseMovieModel>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): Response<BaseMovieDetailsModel>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<BaseMovieModel>


}
package com.igzafer.neizlesem.data.remote

import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.data.remote.models.people.CreditsDataObject
import com.igzafer.neizlesem.data.remote.models.movie.MovieDataObject
import com.igzafer.neizlesem.data.remote.models.movie.MovieDetailData
import com.igzafer.neizlesem.data.remote.models.movie.movie_credits.MovieCreditsObject
import com.igzafer.neizlesem.data.remote.models.movie.movie_images.MovieImagesDataObject
import com.igzafer.neizlesem.data.remote.models.people.PersonDetailData
import com.igzafer.neizlesem.data.remote.models.search.SearchData
import com.igzafer.neizlesem.data.remote.models.search.SearchDataObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NeIzlesemApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): MovieDataObject

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): MovieDetailData

    @GET("movie/{movieId}/credits")
    suspend fun getCast(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): CreditsDataObject

    @GET("movie/{movieId}/recommendations")
    suspend fun getRecommendedMovies(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("page") page: Int
    ): MovieDataObject

    @GET("movie/{movieId}/images")
    suspend fun geMovieImages(
        @Path(value = "movieId", encoded = true) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): MovieImagesDataObject

    @GET("person/{personId}")
    suspend fun getPersonDetails(
        @Path(value = "personId", encoded = true) personId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): PersonDetailData

    @GET("person/{personId}/movie_credits")
    suspend fun getPersonMovieCredits(
        @Path(value = "personId", encoded = true) personId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
    ): MovieCreditsObject

    @GET("search/multi")
    suspend fun searchMultiData(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = BuildConfig.LANGUAGE,
        @Query("query") query: String,
        @Query("page") page: Int
    ):SearchDataObject
}
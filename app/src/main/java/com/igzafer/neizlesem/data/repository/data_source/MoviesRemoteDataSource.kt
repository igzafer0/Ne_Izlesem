package com.igzafer.neizlesem.data.repository.data_source

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Response<BaseMovieModel>
    suspend fun getNowPlayingMovies(page: Int): Response<BaseMovieModel>
    suspend fun getUpcomingMovies(page: Int): Response<BaseMovieModel>
    suspend fun getTrendingWeeklyMovies(): Response<BaseMovieModel>
    suspend fun discoverMovies(page: Int, genres: Int): Response<BaseMovieModel>
}
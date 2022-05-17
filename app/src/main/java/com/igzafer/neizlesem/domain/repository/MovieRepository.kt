package com.igzafer.neizlesem.domain.repository

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.util.Resource
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): Resource<BaseMovieModel>
    suspend fun getNowPlayingMovies(page: Int): Resource<BaseMovieModel>
    suspend fun getUpcomingMovies(page: Int): Resource<BaseMovieModel>
    suspend fun getTrendingWeeklyMovies(): Resource<BaseMovieModel>
    suspend fun discoverMovies(page: Int, genres: Int): Resource<BaseMovieModel>

}
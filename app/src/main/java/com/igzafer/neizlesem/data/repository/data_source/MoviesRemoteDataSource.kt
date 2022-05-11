package com.igzafer.neizlesem.data.repository.data_source

import com.igzafer.neizlesem.data.model.BaseMovieModel
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Response<BaseMovieModel>
    suspend fun getNowPlayingMovies(page: Int): Response<BaseMovieModel>
}
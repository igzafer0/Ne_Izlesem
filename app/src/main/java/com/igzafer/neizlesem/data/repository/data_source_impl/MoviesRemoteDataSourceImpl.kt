package com.igzafer.neizlesem.data.repository.data_source_impl

import com.igzafer.neizlesem.data.api.ApiService
import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import retrofit2.Response

class MoviesRemoteDataSourceImpl(private val apiService: ApiService) : MoviesRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Response<BaseMovieModel> {
        return apiService.getPopularMovies(page = page)
    }

    override suspend fun getNowPlayingMovies(page: Int): Response<BaseMovieModel> {
        return apiService.getNowPlayingMovies(page = page)
    }

    override suspend fun getUpcomingMovies(page: Int): Response<BaseMovieModel> {
        return apiService.getUpcomingMovies(page = page)
    }

    override suspend fun getTrendingWeeklyMovies(): Response<BaseMovieModel> {
        return apiService.getTrendingWeeklyMovies()
    }

    override suspend fun discoverMovies(page: Int, genres: Int): Response<BaseMovieModel> {
        return apiService.discoverMovie(page = page, genres = genres)
    }

}
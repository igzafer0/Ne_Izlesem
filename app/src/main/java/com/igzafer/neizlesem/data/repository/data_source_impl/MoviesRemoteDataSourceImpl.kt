package com.igzafer.neizlesem.data.repository.data_source_impl

import com.igzafer.neizlesem.data.api.ApiService
import com.igzafer.neizlesem.data.model.BaseMovieModel
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import retrofit2.Response

class MoviesRemoteDataSourceImpl(private val apiService: ApiService):MoviesRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Response<BaseMovieModel> {
        return apiService.getPopularMovies(page=page)
    }
}
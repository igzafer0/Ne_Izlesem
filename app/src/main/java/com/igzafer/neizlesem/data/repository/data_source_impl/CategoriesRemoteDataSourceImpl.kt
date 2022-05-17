package com.igzafer.neizlesem.data.repository.data_source_impl

import com.igzafer.neizlesem.data.api.ApiService
import com.igzafer.neizlesem.data.model.category.BaseCategoryModel
import com.igzafer.neizlesem.data.repository.data_source.CategoriesRemoteDataSource
import retrofit2.Response

class CategoriesRemoteDataSourceImpl(private val apiService: ApiService):CategoriesRemoteDataSource {
    override suspend fun getMoviesCategory(): Response<BaseCategoryModel> {
        return apiService.getMoviesGenre()
    }

    override suspend fun getTvSeriesCategory(): Response<BaseCategoryModel> {
        return apiService.getTvShowsGenre()
    }
}
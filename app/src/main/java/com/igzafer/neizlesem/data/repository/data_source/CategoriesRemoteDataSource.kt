package com.igzafer.neizlesem.data.repository.data_source


import com.igzafer.neizlesem.data.model.category.BaseCategoryModel
import retrofit2.Response

interface CategoriesRemoteDataSource {
    suspend fun getMoviesCategory(): Response<BaseCategoryModel>
    suspend fun getTvSeriesCategory(): Response<BaseCategoryModel>
}
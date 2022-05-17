package com.igzafer.neizlesem.data.repository

import com.igzafer.neizlesem.data.model.category.BaseCategoryModel
import com.igzafer.neizlesem.data.repository.data_source.CategoriesRemoteDataSource
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.CategoryRepository
import retrofit2.Response

class CategoriesRepositoryImpl(private val remoteDataSource: CategoriesRemoteDataSource):CategoryRepository {
    override suspend fun getMoviesCategory(): Resource<BaseCategoryModel> {
        return responseToResource(remoteDataSource.getMoviesCategory())
    }

    override suspend fun getTvSeriesCategory(): Resource<BaseCategoryModel> {
        return responseToResource(remoteDataSource.getTvSeriesCategory())

    }
    private fun responseToResource(response: Response<BaseCategoryModel>): Resource<BaseCategoryModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}
package com.igzafer.neizlesem.domain.repository

import com.igzafer.neizlesem.data.model.category.BaseCategoryModel
import com.igzafer.neizlesem.data.util.Resource
import retrofit2.Response

interface CategoryRepository {
    suspend fun getMoviesCategory(): Resource<BaseCategoryModel>
    suspend fun getTvSeriesCategory(): Resource<BaseCategoryModel>

}
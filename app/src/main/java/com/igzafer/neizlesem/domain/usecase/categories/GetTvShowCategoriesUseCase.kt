package com.igzafer.neizlesem.domain.usecase.categories

import com.igzafer.neizlesem.data.model.category.BaseCategoryModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.CategoryRepository

class GetTvShowCategoriesUseCase (private val categoryRepository: CategoryRepository) {
    suspend fun execute(): Resource<BaseCategoryModel> {
        return categoryRepository.getTvSeriesCategory()
    }
}
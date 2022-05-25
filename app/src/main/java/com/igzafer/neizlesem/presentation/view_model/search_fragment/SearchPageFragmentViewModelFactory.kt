package com.igzafer.neizlesem.presentation.view_model.search_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.actors.GetPopularActorsUseCase
import com.igzafer.neizlesem.domain.usecase.categories.GetMovieCategoriesUseCase

@Suppress("UNCHECKED_CAST")
class SearchPageFragmentViewModelFactory(
    private val app: Application,
    private val getPopularActorsUseCase: GetPopularActorsUseCase,
    private val getMovieCategoriesUseCase: GetMovieCategoriesUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchPageFragmentViewModel(
            app,
            getPopularActorsUseCase,
            getMovieCategoriesUseCase
        ) as T
    }

}
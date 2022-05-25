package com.igzafer.neizlesem.presentation.view_model.category_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.movies.*

@Suppress("UNCHECKED_CAST")
class MovieCategoryFragmentViewModelFactory(
    private val app: Application,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieCategoryFragmentViewModel(
            app,
            discoverMoviesUseCase
        ) as T
    }

}
package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.actors.GetPopularActorsUseCase
import com.igzafer.neizlesem.domain.usecase.categories.GetMovieCategoriesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.SearchMovieUseCase

@Suppress("UNCHECKED_CAST")
class SearchFragmentViewModelFactory(
    private val app: Application,
    private val searchMovieUseCase: SearchMovieUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchFragmentViewModel(
            app,
            searchMovieUseCase
        ) as T
    }

}
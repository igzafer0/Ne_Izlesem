package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.actors.GetPopularActorsUseCase
import com.igzafer.neizlesem.domain.usecase.categories.GetMovieCategoriesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetNowPlayingMovieUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetPopularMoviesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetTrendingWeeklyMoviesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetUpcomingMovieUseCase

@Suppress("UNCHECKED_CAST")
class SearchFragmentViewModelFactory(
    private val app: Application,
    private val getPopularActorsUseCase: GetPopularActorsUseCase,
    private val getMovieCategoriesUseCase: GetMovieCategoriesUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchFragmentViewModel(
            app,
            getPopularActorsUseCase,
            getMovieCategoriesUseCase
        ) as T
    }

}
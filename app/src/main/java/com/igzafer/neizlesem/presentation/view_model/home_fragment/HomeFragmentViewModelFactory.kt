package com.igzafer.neizlesem.presentation.view_model.home_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.movies.GetNowPlayingMovieUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetPopularMoviesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetTrendingWeeklyMoviesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetUpcomingMovieUseCase

@Suppress("UNCHECKED_CAST")
class HomeFragmentViewModelFactory(
    private val app: Application,
    private val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTrendingWeeklyMoviesUseCase: GetTrendingWeeklyMoviesUseCase,
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase,

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(
            app,
            getNowPlayingMovieUseCase,
            getPopularMoviesUseCase,
            getTrendingWeeklyMoviesUseCase,
            getUpcomingMovieUseCase
        ) as T
    }

}
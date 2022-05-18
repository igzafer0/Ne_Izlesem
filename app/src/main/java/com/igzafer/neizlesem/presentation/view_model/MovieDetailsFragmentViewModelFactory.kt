package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.movies.DiscoverMoviesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetMovieDetailsUseCase

@Suppress("UNCHECKED_CAST")
class MovieDetailsFragmentViewModelFactory (
    private val app: Application,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsFragmentViewModel(
            app,
            getMovieDetailsUseCase
        ) as T

    }

}
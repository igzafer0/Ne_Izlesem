package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.GetNowPlayingMovieUseCase
import com.igzafer.neizlesem.domain.usecase.GetPopularMoviesUseCase

@Suppress("UNCHECKED_CAST")
class NowPlayingMoviesViewModelFactory (private val app: Application,
                                        private val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NowPlayingMoviesViewModel(app, getNowPlayingMovieUseCase) as T
    }

}
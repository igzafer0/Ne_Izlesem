package com.igzafer.neizlesem.presentation.view_model.details_fragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igzafer.neizlesem.domain.usecase.actors.GetCastUseCase
import com.igzafer.neizlesem.domain.usecase.movies.*

@Suppress("UNCHECKED_CAST")
class MovieDetailsFragmentViewModelFactory(
    private val app: Application,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val getCastUseCase: GetCastUseCase,
    private val isExistMovieUseCase: IsExistMovieUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsFragmentViewModel(
            app,
            getMovieDetailsUseCase,
            deleteSavedMoviesUseCase,
            saveMoviesUseCase,
            getCastUseCase,
            isExistMovieUseCase,
            getMovieImagesUseCase
        ) as T

    }

}
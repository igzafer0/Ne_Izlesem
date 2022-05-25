package com.igzafer.neizlesem.presentation.view_model.saved_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.usecase.movies.*
import kotlinx.coroutines.launch

class SavedPageFragmentViewModel(
    private val app: Application,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,


    ) : AndroidViewModel(app) {

    fun getSavedMovies() = liveData {
        getSavedMoviesUseCase.execute().collect {
            emit(it)
        }
    }



}
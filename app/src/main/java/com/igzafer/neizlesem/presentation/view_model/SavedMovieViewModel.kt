package com.igzafer.neizlesem.presentation.view_model

import androidx.lifecycle.ViewModel
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SavedMovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

     fun getMovies(): Flow<List<MovieDetailsModel>> {
        return repository.getSavedMovies()
    }
}
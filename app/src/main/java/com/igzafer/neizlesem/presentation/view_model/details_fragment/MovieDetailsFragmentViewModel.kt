package com.igzafer.neizlesem.presentation.view_model.details_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.igzafer.neizlesem.data.model.actor.BaseCastModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.data.model.movie.movie_images.BaseMovieImagesModel
import com.igzafer.neizlesem.domain.usecase.actors.GetCastUseCase
import com.igzafer.neizlesem.domain.usecase.movies.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MovieDetailsFragmentViewModel(
    private val app: Application,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val getCastUseCase: GetCastUseCase,
    private val isExistMovieUseCase: IsExistMovieUseCase,
    private val getMoviesImagesUseCase: GetMovieImagesUseCase

) : AndroidViewModel(app) {

    suspend fun getMovieDetails(movieId: Int): BaseMovieDetailsModel {
        return getMovieDetailsUseCase.execute(movieId).data!!
    }


    suspend fun getCast(movieId: Int): BaseCastModel{
        return getCastUseCase.execute(movieId).data!!
    }

    suspend fun getMoviesImages(movieId: Int):BaseMovieImagesModel?{
        return getMoviesImagesUseCase.execute(movieId).data!!
    }

    fun saveMovie(moviesModel: MoviesModel) = viewModelScope.launch {
        saveMoviesUseCase.execute(moviesModel)
    }

    suspend fun isExistMovie(id: Int): MoviesModel? {
        return isExistMovieUseCase.execute(id)
    }

    fun deleteMovie(moviesId: Int) = viewModelScope.launch {
        deleteSavedMoviesUseCase.execute(moviesId)
    }
}
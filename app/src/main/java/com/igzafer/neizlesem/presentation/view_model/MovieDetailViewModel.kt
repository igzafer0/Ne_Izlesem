package com.igzafer.neizlesem.presentation.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.igzafer.neizlesem.data.remote.models.people.toCreditsModel
import com.igzafer.neizlesem.data.remote.models.movie.movie_images.toMoviesModel
import com.igzafer.neizlesem.data.remote.models.movie.toMoviesModel
import com.igzafer.neizlesem.domain.model.CreditsModel
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MovieImagesModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.igzafer.neizlesem.domain.repository.PeopleRepository
import com.igzafer.neizlesem.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val peopleRepository: PeopleRepository,
) : ViewModel() {


    val isSaved: MutableLiveData<Boolean> = MutableLiveData(false)
    suspend fun getMovieDetail(movieId: Int): MovieDetailsModel {
        isMovieSaved(movieId)
        return movieRepository.getMovieDetail(movieId).toMoviesModel()
    }

    private suspend fun isMovieSaved(movieId: Int) {
        isSaved.value = movieRepository.isMovieExist(movieId) != null
    }


    suspend fun getCredits(movieId: Int): List<CreditsModel> {
        val baseData = peopleRepository.getCredits(movieId)
        val crewData = baseData.crew.toCreditsModel()
        val castData = baseData.cast.toCreditsModel()
        val returnData = mutableListOf<CreditsModel>()
        for (i in crewData.indices) {
            if (crewData[i].role == "Yönetmen") {
                returnData.add(crewData[i])
            }
        }
        returnData.addAll(castData)
        return returnData
    }

    suspend fun getRecommendedMovies(movieId: Int): Flow<PagingData<MoviesModel>> {
        return movieRepository.getRecommendedMovies(movieId = movieId).toMoviesModel()
    }

    suspend fun getMovieImages(movieId: Int): List<MovieImagesModel> {
        return movieRepository.getMovieImages(movieId).toMoviesModel()
    }

    private suspend fun saveMovie(movieDetailsModel: MovieDetailsModel) {
        movieRepository.saveMovie(movieDetailsModel)
    }

    private suspend fun deleteMovie(movieDetailsModel: MovieDetailsModel) {
        movieRepository.deleteMovie(movieDetailsModel)
    }

    fun saveButtonClick(movieDetailsModel: MovieDetailsModel) {
        if (isSaved.value == false) {
            viewModelScope.launch {
                saveMovie(movieDetailsModel)
            }
        } else {
            viewModelScope.launch {
                deleteMovie(movieDetailsModel)
            }
        }
        isSaved.value = isSaved.value!!.not()
    }
}
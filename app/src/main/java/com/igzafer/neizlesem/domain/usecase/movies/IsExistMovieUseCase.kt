package com.igzafer.neizlesem.domain.usecase.movies

import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class IsExistMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(id: Int): MoviesModel {
        return movieRepository.isMovieExist(id)
    }
}
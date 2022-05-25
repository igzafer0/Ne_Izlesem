package com.igzafer.neizlesem.domain.usecase.movies

import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.repository.MovieRepository

class DeleteSavedMoviesUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(moviesId: Int)=movieRepository.deleteMovie(moviesId)
}
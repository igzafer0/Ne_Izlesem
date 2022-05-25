package com.igzafer.neizlesem.domain.usecase.movies

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.repository.MovieRepository

class SaveMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(moviesModel: MoviesModel) {
        movieRepository.saveMovie(moviesModel)
    }
}
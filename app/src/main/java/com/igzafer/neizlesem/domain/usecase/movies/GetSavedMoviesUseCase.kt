package com.igzafer.neizlesem.domain.usecase.movies

import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetSavedMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): Flow<List<MoviesModel>> {
        return movieRepository.getSavedMovies()
    }
}
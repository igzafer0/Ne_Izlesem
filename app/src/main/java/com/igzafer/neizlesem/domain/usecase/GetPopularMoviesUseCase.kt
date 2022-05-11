package com.igzafer.neizlesem.domain.usecase

import com.igzafer.neizlesem.data.model.BaseMovieModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.MovieRepository

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(page: Int): Resource<BaseMovieModel> {
        return movieRepository.getPopularMovies(page)
    }
}
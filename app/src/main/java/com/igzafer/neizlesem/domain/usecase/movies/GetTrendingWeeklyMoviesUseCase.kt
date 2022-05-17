package com.igzafer.neizlesem.domain.usecase.movies

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.MovieRepository

class GetTrendingWeeklyMoviesUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(): Resource<BaseMovieModel> {
        return movieRepository.getTrendingWeeklyMovies()
    }
}
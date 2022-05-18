package com.igzafer.neizlesem.domain.usecase.movies

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(movieId: Int): Resource<BaseMovieDetailsModel> {
        return movieRepository.getMovieDetails(movieId)
    }
}
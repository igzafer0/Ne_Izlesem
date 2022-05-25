package com.igzafer.neizlesem.domain.usecase.movies

import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.data.model.movie.movie_images.BaseMovieImagesModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.MovieRepository

class GetMovieImagesUseCase (private val movieRepository: MovieRepository) {
    suspend fun execute(movieId: Int): Resource<BaseMovieImagesModel> {
        return movieRepository.getMovieImages(movieId)
    }
}
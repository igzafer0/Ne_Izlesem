package com.igzafer.neizlesem.domain.repository

import androidx.paging.PagingData
import com.igzafer.neizlesem.data.remote.models.movie.MovieData
import com.igzafer.neizlesem.data.remote.models.movie.MovieDataObject
import com.igzafer.neizlesem.data.remote.models.movie.MovieDetailData
import com.igzafer.neizlesem.data.remote.models.movie.movie_images.MovieImagesData
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): MovieDataObject
    suspend fun getMovieDetail(movieId: Int): MovieDetailData
    suspend fun getRecommendedMovies(movieId: Int): Flow<PagingData<MovieData>>
    suspend fun getMovieImages(movieId: Int): List<MovieImagesData>


    //local
    suspend fun saveMovie(movieModel: MovieDetailsModel)
    suspend fun deleteMovie(movieModel: MovieDetailsModel)
    fun getSavedMovies(): Flow<List<MovieDetailsModel>>
    suspend fun isMovieExist(id: Int): MovieDetailsModel?
}
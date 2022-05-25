package com.igzafer.neizlesem.domain.repository

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.data.model.movie.movie_images.BaseMovieImagesModel
import com.igzafer.neizlesem.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {
    //remote
    suspend fun getPopularMovies(page: Int): Resource<BaseMovieModel>
    suspend fun getNowPlayingMovies(page: Int): Resource<BaseMovieModel>
    suspend fun getUpcomingMovies(page: Int): Resource<BaseMovieModel>
    suspend fun getTrendingWeeklyMovies(): Resource<BaseMovieModel>
    suspend fun discoverMovies(page: Int, genres: Int): Resource<BaseMovieModel>
    suspend fun getMovieDetails(movieId: Int): Resource<BaseMovieDetailsModel>
    suspend fun searchMovie(query: String, page: Int): Resource<BaseMovieModel>
    suspend fun getMovieImages(movieId: Int): Resource<BaseMovieImagesModel>

    //local
    suspend fun saveMovie(movieModel: MoviesModel)
    suspend fun deleteMovie(moviesId: Int)
    fun getSavedMovies(): Flow<List<MoviesModel>>
    suspend fun isMovieExist(id: Int): MoviesModel


}
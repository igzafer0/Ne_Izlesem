package com.igzafer.neizlesem.data.repository.data_source


import com.igzafer.neizlesem.data.model.movie.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    suspend fun saveMovie(moviesModel: MoviesModel)
    fun getSavedMovies(): Flow<List<MoviesModel>>
    suspend fun deleteMoviesFromDB(movieId: Int)
    suspend fun isMovieExist(id: Int):MoviesModel
}
package com.igzafer.neizlesem.data.repository.data_source_impl

import com.igzafer.neizlesem.data.database.NeIzlesemDao
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.data.repository.data_source.MoviesLocalDataSource
import kotlinx.coroutines.flow.Flow

class MoviesLocalDataSourceImpl(private val dao: NeIzlesemDao) : MoviesLocalDataSource {
    override suspend fun saveMovie(moviesModel: MoviesModel) {
        dao.insertMovie(moviesModel)
    }

    override fun getSavedMovies(): Flow<List<MoviesModel>> {
       return dao.getAllMovies()
    }

    override suspend fun deleteMoviesFromDB(movieId: Int) {
        dao.deleteMovie(movieId)
    }

    override suspend fun isMovieExist(id: Int): MoviesModel {
        return dao.isDataExist(id)
    }
}
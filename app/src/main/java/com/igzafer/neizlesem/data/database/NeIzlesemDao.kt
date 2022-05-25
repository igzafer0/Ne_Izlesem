package com.igzafer.neizlesem.data.database

import androidx.room.*
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NeIzlesemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MoviesModel)

    @Query("SELECT * FROM movies order by databaseMovieId desc")
    fun getAllMovies(): Flow<List<MoviesModel>>

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("SELECT * FROM movies where id = :movieId")
    suspend fun isDataExist(movieId: Int): MoviesModel
}
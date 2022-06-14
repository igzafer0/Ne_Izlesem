package com.igzafer.neizlesem.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NeIzlesemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieDetailsModel)

    @Query("SELECT * FROM moviedetailsmodel order by movieId desc")
    fun getAllMovies(): Flow<List<MovieDetailsModel>>

    @Query("DELETE FROM moviedetailsmodel WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("SELECT * FROM moviedetailsmodel where id = :movieId")
    suspend fun isDataExist(movieId: Int): MovieDetailsModel
}
package com.igzafer.neizlesem.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.igzafer.neizlesem.data.remote.models.movie.MovieGenreObject

@Entity(tableName = "moviedetailsmodel")
data class MovieDetailsModel(
    @PrimaryKey(autoGenerate = true)
    val movieId: Int,
    val backdropPath: String,
    val posterPath: String,
    val genres: List<MovieGenreObject>,
    val id: Int,
    val overview: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
)
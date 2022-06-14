package com.igzafer.neizlesem.data.remote.models.movie

import androidx.paging.PagingData
import androidx.paging.map
import com.igzafer.neizlesem.domain.model.MoviesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class MovieData(
    val adult: Boolean?,
    val backdrop_path: String?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun Flow<PagingData<MovieData>>.toMoviesModel(): Flow<PagingData<MoviesModel>> {
    return map { paging ->
        paging.map {
            MoviesModel(
                id = it.id!!,
                posterPath = it.poster_path,
                title = it.title!!,
                releaseDate = it.release_date,
                voteAverage = it.vote_average!!
            )
        }
    }
}

fun List<MovieData>.toMoviesModel(): List<MoviesModel> {
    return map {
        MoviesModel(
            id = it.id!!,
            posterPath = it.poster_path,
            title = it.title!!,
            releaseDate = it.release_date,
            voteAverage = it.vote_average!!
        )
    }
}



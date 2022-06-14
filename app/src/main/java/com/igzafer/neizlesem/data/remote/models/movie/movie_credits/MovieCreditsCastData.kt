package com.igzafer.neizlesem.data.remote.models.movie.movie_credits

import com.igzafer.neizlesem.domain.model.MovieCreditsModel


data class MovieCreditsCastData(
    val adult: Boolean?,
    val backdrop_path: String?,
    val character: String?,
    val credit_id: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val order: Int?,
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

fun List<MovieCreditsCastData>.toMovieCreditsModel(): List<MovieCreditsModel> {
    return map{
        MovieCreditsModel(
            id = it.id!!,
            title = it.title!!,
            job = it.character!!,
            posterPath = it.poster_path ?: ""
        )
    }
}
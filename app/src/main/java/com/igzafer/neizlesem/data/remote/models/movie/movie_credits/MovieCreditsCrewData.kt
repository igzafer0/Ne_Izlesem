package com.igzafer.neizlesem.data.remote.models.movie.movie_credits

import com.igzafer.neizlesem.data.util.sozluk
import com.igzafer.neizlesem.domain.model.MovieCreditsModel


data class MovieCreditsCrewData(
    val adult: Boolean?,
    val backdropPath: String?,
    val credit_id: String?,
    val department: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val job: String?,
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

fun MovieCreditsCrewData.toMovieCreditsModel(): MovieCreditsModel {
    return MovieCreditsModel(
        id = id!!,
        title = title!!,
        job = sozluk(job!!),
        posterPath = poster_path ?: ""
    )

}

fun List<MovieCreditsCrewData>.toMovieCreditsModel(): List<MovieCreditsModel> {
    return map {
        MovieCreditsModel(
            id = it.id!!,
            title = it.title!!,
            job = sozluk(it.job!!),
            posterPath = it.poster_path ?: ""
        )
    }
}
package com.igzafer.neizlesem.data.remote.models.movie

import androidx.paging.PagingData
import androidx.paging.map
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.io.path.createTempDirectory

data class MovieDetailData(
    val adult: Boolean?,
    val backdrop_path: String?,
    val budget: Int?,
    val genres: List<MovieGenreObject>?,
    val homepage: String?,
    val id: Int?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Long?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun MovieDetailData.toMoviesModel(): MovieDetailsModel {
    return MovieDetailsModel(
        movieId = 0,
        id = id!!,
        title = title!!,
        releaseDate = release_date!!,
        voteAverage = vote_average!!,
        posterPath = poster_path ?: "",
        backdropPath = backdrop_path ?: "",
        //dee bradley baker
        genres = genres!!,
        overview = if (overview == "" || overview == null) "Bu yapım hakkında bir konu bilgisi girilmemiş." else overview,
        revenue = revenue!!,
        runtime = runtime!!,
        video = video!!
    )
}

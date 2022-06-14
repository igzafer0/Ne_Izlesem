package com.igzafer.neizlesem.data.remote.models.movie.movie_images

import com.igzafer.neizlesem.data.remote.models.movie.MovieDetailData
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MovieImagesModel
import com.igzafer.neizlesem.domain.model.MoviesModel

data class MovieImagesData(
    val aspect_ratio: Double?,
    val file_path: String?,
    val height: Int?,
    val iso_639_1: Any?,
    val vote_average: Double?,
    val vote_count: Int?,
    val width: Int?
)

fun List<MovieImagesData>.toMoviesModel(): List<MovieImagesModel> {
    return map {
        MovieImagesModel(
            filePath = it.file_path!!
        )
    }
}


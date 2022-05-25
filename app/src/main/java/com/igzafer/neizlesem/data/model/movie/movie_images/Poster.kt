package com.igzafer.neizlesem.data.model.movie.movie_images


import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("aspect_ratio")
    val aspectRatio: Double,
    @SerializedName("file_path")
    val filePath: String,
    val height: Int,
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val width: Int
)
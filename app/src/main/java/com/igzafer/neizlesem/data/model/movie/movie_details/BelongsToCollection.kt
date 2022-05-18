package com.igzafer.neizlesem.data.model.movie.movie_details


import com.google.gson.annotations.SerializedName

data class BelongsToCollection(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)
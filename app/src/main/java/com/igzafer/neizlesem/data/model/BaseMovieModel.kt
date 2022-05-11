package com.igzafer.neizlesem.data.model


import com.google.gson.annotations.SerializedName

data class BaseMovieModel(
    val page: Int,
    @SerializedName("results")
    val moviesModels: List<MoviesModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
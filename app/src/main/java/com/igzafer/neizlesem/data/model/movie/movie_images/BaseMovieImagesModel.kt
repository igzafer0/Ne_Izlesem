package com.igzafer.neizlesem.data.model.movie.movie_images


import com.google.gson.annotations.SerializedName

data class BaseMovieImagesModel(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)
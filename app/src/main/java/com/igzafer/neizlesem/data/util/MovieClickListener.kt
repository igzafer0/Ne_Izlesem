package com.igzafer.neizlesem.data.util

import com.igzafer.neizlesem.data.model.movie.MoviesModel

var onItemClickListenerMovie: ((MoviesModel) -> Unit)? = null
fun setOnClickItemListenerMovie(listener: (MoviesModel) -> Unit) {
    onItemClickListenerMovie = listener
}
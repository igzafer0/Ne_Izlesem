package com.igzafer.neizlesem.data.remote.models.movie.movie_credits


data class MovieCreditsObject(
    val cast: List<MovieCreditsCastData>,
    val crew: List<MovieCreditsCrewData>,
    val id: Int
)
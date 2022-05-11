package com.igzafer.neizlesem.domain.repository

import com.igzafer.neizlesem.data.model.BaseMovieModel
import com.igzafer.neizlesem.data.util.Resource

interface MovieRepository {
    suspend fun getPopularMovies(page: Int): Resource<BaseMovieModel>
}
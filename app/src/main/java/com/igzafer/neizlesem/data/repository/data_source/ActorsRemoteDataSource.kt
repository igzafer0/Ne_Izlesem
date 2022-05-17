package com.igzafer.neizlesem.data.repository.data_source

import com.igzafer.neizlesem.data.model.actor.BaseActorModel
import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import retrofit2.Response

interface ActorsRemoteDataSource {
    suspend fun getPopularActors(page: Int): Response<BaseActorModel>
}
package com.igzafer.neizlesem.data.repository.data_source

import com.igzafer.neizlesem.data.model.actor.BaseCastModel
import com.igzafer.neizlesem.data.model.actor.actor_details.BaseActorModel
import retrofit2.Response

interface ActorsRemoteDataSource {
    suspend fun getPopularActors(page: Int): Response<BaseActorModel>
    suspend fun getCast(movieId: Int): Response<BaseCastModel>
}
package com.igzafer.neizlesem.domain.repository

import com.igzafer.neizlesem.data.model.actor.BaseCastModel
import com.igzafer.neizlesem.data.model.actor.actor_details.BaseActorModel
import com.igzafer.neizlesem.data.util.Resource

interface ActorRepository {
    suspend fun getPopularActors(page: Int): Resource<BaseActorModel>
    suspend fun getCast(movieId: Int): Resource<BaseCastModel>

}
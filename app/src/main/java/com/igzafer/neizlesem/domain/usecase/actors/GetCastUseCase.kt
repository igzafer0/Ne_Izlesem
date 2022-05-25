package com.igzafer.neizlesem.domain.usecase.actors

import com.igzafer.neizlesem.data.model.actor.BaseCastModel
import com.igzafer.neizlesem.data.model.actor.actor_details.BaseActorModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.ActorRepository

class GetCastUseCase(private val actorRepository: ActorRepository) {
    suspend fun execute(movieId: Int): Resource<BaseCastModel> {
        return actorRepository.getCast(movieId)
    }
}
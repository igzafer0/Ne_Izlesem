package com.igzafer.neizlesem.domain.usecase.actors

import com.igzafer.neizlesem.data.model.actor.actor_details.BaseActorModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.ActorRepository

class GetPopularActorsUseCase(private val actorRepository: ActorRepository) {
    suspend fun execute(page: Int): Resource<BaseActorModel> {
        return actorRepository.getPopularActors(page)
    }
}
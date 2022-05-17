package com.igzafer.neizlesem.domain.usecase.actors

import com.igzafer.neizlesem.data.model.actor.BaseActorModel
import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.ActorRepository
import com.igzafer.neizlesem.domain.repository.MovieRepository

class GetPopularActorsUseCase(private val actorRepository: ActorRepository) {
    suspend fun execute(page: Int): Resource<BaseActorModel> {
        return actorRepository.getPopularActors(page)
    }
}
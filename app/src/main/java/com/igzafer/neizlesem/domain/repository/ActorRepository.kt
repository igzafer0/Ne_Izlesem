package com.igzafer.neizlesem.domain.repository

import com.igzafer.neizlesem.data.model.actor.BaseActorModel
import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.util.Resource

interface ActorRepository {
    suspend fun getPopularActors(page: Int): Resource<BaseActorModel>

}
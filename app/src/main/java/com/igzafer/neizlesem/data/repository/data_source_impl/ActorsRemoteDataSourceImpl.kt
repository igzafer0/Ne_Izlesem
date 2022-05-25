package com.igzafer.neizlesem.data.repository.data_source_impl

import com.igzafer.neizlesem.data.api.ApiService
import com.igzafer.neizlesem.data.model.actor.BaseCastModel
import com.igzafer.neizlesem.data.model.actor.actor_details.BaseActorModel
import com.igzafer.neizlesem.data.repository.data_source.ActorsRemoteDataSource
import retrofit2.Response

class ActorsRemoteDataSourceImpl(val apiService: ApiService) : ActorsRemoteDataSource {
    override suspend fun getPopularActors(page: Int): Response<BaseActorModel> {
        return apiService.getPopularActors(page = page)

    }

    override suspend fun getCast(movieId: Int): Response<BaseCastModel> {
        return apiService.getCast(movieId = movieId)
    }
}
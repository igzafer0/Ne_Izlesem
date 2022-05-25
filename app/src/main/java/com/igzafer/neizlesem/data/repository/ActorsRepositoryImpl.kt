package com.igzafer.neizlesem.data.repository

import com.igzafer.neizlesem.data.model.actor.BaseCastModel
import com.igzafer.neizlesem.data.model.actor.actor_details.BaseActorModel
import com.igzafer.neizlesem.data.repository.data_source.ActorsRemoteDataSource
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.ActorRepository
import retrofit2.Response

class ActorsRepositoryImpl(private val remoteDataSource: ActorsRemoteDataSource) : ActorRepository {
    override suspend fun getPopularActors(page: Int): Resource<BaseActorModel> {
        return responseToResource(remoteDataSource.getPopularActors(page))
    }

    override suspend fun getCast(movieId: Int): Resource<BaseCastModel> {
        return responseToResourceCast(remoteDataSource.getCast(movieId))
    }

    private fun responseToResource(response: Response<BaseActorModel>): Resource<BaseActorModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
    private fun responseToResourceCast(response: Response<BaseCastModel>): Resource<BaseCastModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}
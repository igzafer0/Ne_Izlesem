package com.igzafer.neizlesem.data.repository

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.MovieRepository
import retrofit2.Response

class MovieRepositoryImpl(private val remoteDataSource: MoviesRemoteDataSource) : MovieRepository {
    override suspend fun getPopularMovies(page: Int): Resource<BaseMovieModel> {
        return responseToResource(remoteDataSource.getPopularMovies(page))
    }

    override suspend fun getNowPlayingMovies(page: Int): Resource<BaseMovieModel> {
        return responseToResource(remoteDataSource.getNowPlayingMovies(page))
    }

    override suspend fun getUpcomingMovies(page: Int): Resource<BaseMovieModel> {
        return responseToResource(remoteDataSource.getUpcomingMovies(page))
    }

    override suspend fun getTrendingWeeklyMovies(): Resource<BaseMovieModel> {
        return responseToResource(remoteDataSource.getTrendingWeeklyMovies())
    }

    override suspend fun discoverMovies(page: Int, genres: Int): Resource<BaseMovieModel> {
        return responseToResource(remoteDataSource.discoverMovies(page, genres))
    }

    private fun responseToResource(response: Response<BaseMovieModel>): Resource<BaseMovieModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
}
package com.igzafer.neizlesem.data.repository

import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import com.igzafer.neizlesem.data.model.movie.movie_images.BaseMovieImagesModel
import com.igzafer.neizlesem.data.repository.data_source.MoviesLocalDataSource
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import com.igzafer.neizlesem.data.util.Resource
import com.igzafer.neizlesem.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieRepositoryImpl(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource
) : MovieRepository {
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

    override suspend fun getMovieDetails(movieId: Int): Resource<BaseMovieDetailsModel> {
        return responseToResourceDetails(remoteDataSource.getMovieDetails(movieId = movieId))
    }

    override suspend fun searchMovie(query: String, page: Int): Resource<BaseMovieModel> {
        return responseToResource(remoteDataSource.searchMovie(query, page))
    }

    override suspend fun getMovieImages(movieId: Int): Resource<BaseMovieImagesModel> {
        return responseToResourceImages(remoteDataSource.getMovieImages(movieId))
    }

    override suspend fun saveMovie(movieModel: MoviesModel) {
        localDataSource.saveMovie(movieModel)
    }

    override suspend fun deleteMovie(moviesId: Int) {
        localDataSource.deleteMoviesFromDB(moviesId)
    }

    override fun getSavedMovies(): Flow<List<MoviesModel>> {
        return localDataSource.getSavedMovies()
    }

    override suspend fun isMovieExist(id: Int): MoviesModel {
        return localDataSource.isMovieExist(id)
    }

    private fun responseToResource(response: Response<BaseMovieModel>): Resource<BaseMovieModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourceDetails(response: Response<BaseMovieDetailsModel>): Resource<BaseMovieDetailsModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }
    private fun responseToResourceImages(response: Response<BaseMovieImagesModel>): Resource<BaseMovieImagesModel> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}
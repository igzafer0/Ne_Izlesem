package com.igzafer.neizlesem.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.igzafer.neizlesem.data.local.NeIzlesemDao
import com.igzafer.neizlesem.data.remote.NeIzlesemApi
import com.igzafer.neizlesem.data.remote.models.movie.MovieData
import com.igzafer.neizlesem.data.remote.models.movie.MovieDataObject
import com.igzafer.neizlesem.data.remote.models.movie.MovieDetailData
import com.igzafer.neizlesem.data.remote.models.movie.movie_images.MovieImagesData
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.repository.MovieRepository
import com.igzafer.neizlesem.paging.RecommendedMoviesPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieImpl @Inject constructor(
    private val apiService: NeIzlesemApi,
    private val dao: NeIzlesemDao
) : MovieRepository {
    override suspend fun getPopularMovies(): MovieDataObject {
        return apiService.getPopularMovies()
    }


    override suspend fun getMovieDetail(movieId: Int): MovieDetailData {
        return apiService.getMovieDetails(movieId)
    }

    override suspend fun getRecommendedMovies(movieId: Int): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { RecommendedMoviesPagingDataSource(movieId, apiService) }
        ).flow
    }

    override suspend fun getMovieImages(movieId: Int): List<MovieImagesData> {
        return apiService.geMovieImages(movieId).backdrops
    }

    //local
    override suspend fun saveMovie(movieModel: MovieDetailsModel) {
        return dao.insertMovie(movieModel)
    }

    override suspend fun deleteMovie(movieModel: MovieDetailsModel) {
        return dao.deleteMovie(movieModel.id)
    }

    override fun getSavedMovies(): Flow<List<MovieDetailsModel>> {
        return dao.getAllMovies()
    }

    override suspend fun isMovieExist(id: Int): MovieDetailsModel {
        return dao.isDataExist(id)
    }


}
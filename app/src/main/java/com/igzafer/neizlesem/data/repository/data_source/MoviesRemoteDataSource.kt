package com.igzafer.neizlesem.data.repository.data_source

import android.app.DownloadManager
import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.model.movie.movie_details.BaseMovieDetailsModel
import retrofit2.Response

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Response<BaseMovieModel>
    suspend fun getNowPlayingMovies(page: Int): Response<BaseMovieModel>
    suspend fun getUpcomingMovies(page: Int): Response<BaseMovieModel>
    suspend fun getTrendingWeeklyMovies(): Response<BaseMovieModel>
    suspend fun discoverMovies(page: Int, genres: Int): Response<BaseMovieModel>
    suspend fun getMovieDetails(movieId: Int): Response<BaseMovieDetailsModel>
    suspend fun searchMovie(query: String, page: Int): Response<BaseMovieModel>
}
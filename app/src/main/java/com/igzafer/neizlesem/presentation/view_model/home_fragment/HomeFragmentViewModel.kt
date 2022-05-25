package com.igzafer.neizlesem.presentation.view_model.home_fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.igzafer.neizlesem.data.model.movie.BaseMovieModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.usecase.movies.GetNowPlayingMovieUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetPopularMoviesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetTrendingWeeklyMoviesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.GetUpcomingMovieUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class HomeFragmentViewModel(
    private val app: Application,
    private val getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTrendingWeeklyMoviesUseCase: GetTrendingWeeklyMoviesUseCase,
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
) : AndroidViewModel(app) {
    private var index = 0
    fun getNowPlayingMoviesList(): Flow<PagingData<MoviesModel>> {

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                NowPlayingMoviesPagingSource()
            }).flow
    }

    fun getPopularMoviesList(): Flow<PagingData<MoviesModel>> {

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PopularMoviesPagingSource()
            }).flow
    }

    suspend fun getTrendingWeeklyMoviesList(): BaseMovieModel =
        getTrendingWeeklyMoviesUseCase.execute().data!!


    fun getUpcomingMoviesList(): Flow<PagingData<MoviesModel>> {

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                UpComingMoviesPagingSource()
            }).flow
    }


    private val TMDB_STARTING_PAGE_INDEX = 1

    inner class NowPlayingMoviesPagingSource() : PagingSource<Int, MoviesModel>() {
        override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            try {
                val response = getNowPlayingMovieUseCase.execute(page = pageIndex)
                val movies = response.data?.moviesModels
                if (movies == null) {
                    return LoadResult.Error(Throwable("liste boş"))
                } else {
                    return try {
                        val nextKey =
                            if (movies.isEmpty()) {
                                null
                            } else {
                                // By default, initial load size = 3 * NETWORK PAGE SIZE
                                // ensure we're not requestxing duplicating items at the 2nd request
                                pageIndex + (2)
                            }
                        LoadResult.Page(
                            data = movies,
                            prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                            nextKey = nextKey
                        )
                    } catch (exception: IOException) {
                        return LoadResult.Error(exception)
                    } catch (exception: HttpException) {
                        return LoadResult.Error(exception)
                    }
                }


            } catch (e: Exception) {

                return LoadResult.Error(IOException("no internet"))

            }


        }

    }

    inner class UpComingMoviesPagingSource() : PagingSource<Int, MoviesModel>() {
        override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            try {
                val response = getUpcomingMovieUseCase.execute(page = pageIndex)
                val movies = response.data?.moviesModels
                if (movies == null) {
                    return LoadResult.Error(Throwable("liste boş"))
                } else {
                    return try {
                        val nextKey =
                            if (movies.isEmpty()) {
                                null
                            } else {
                                // By default, initial load size = 3 * NETWORK PAGE SIZE
                                // ensure we're not requestxing duplicating items at the 2nd request
                                pageIndex + (2)
                            }
                        LoadResult.Page(
                            data = movies,
                            prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                            nextKey = nextKey
                        )
                    } catch (exception: IOException) {
                        return LoadResult.Error(exception)
                    } catch (exception: HttpException) {
                        return LoadResult.Error(exception)
                    }
                }


            } catch (e: Exception) {

                return LoadResult.Error(IOException("no internet"))

            }


        }

    }

    inner class PopularMoviesPagingSource() : PagingSource<Int, MoviesModel>() {
        override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
            Log.d("winter", "tetiklendi")
            return state.anchorPosition
        }


        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            try {
                val response = getPopularMoviesUseCase.execute(page = pageIndex)
                Log.d("winter", params.key.toString())
                val movies = response.data?.moviesModels
                if (movies == null) {
                    return LoadResult.Error(Throwable("liste boş"))
                } else {
                    return try {
                        val nextKey =
                            if (movies.isEmpty()) {
                                null
                            } else {
                                // By default, initial load size = 3 * NETWORK PAGE SIZE
                                // ensure we're not requestxing duplicating items at the 2nd request
                                pageIndex + (2)
                            }
                        LoadResult.Page(
                            data = movies,
                            prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                            nextKey = nextKey
                        )
                    } catch (exception: IOException) {
                        return LoadResult.Error(exception)
                    } catch (exception: HttpException) {
                        return LoadResult.Error(exception)
                    }
                }


            } catch (e: Exception) {

                return LoadResult.Error(IOException("no internet"))

            }


        }

    }
}

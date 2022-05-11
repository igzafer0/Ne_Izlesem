package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.*
import com.igzafer.neizlesem.data.model.MoviesModel
import com.igzafer.neizlesem.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class PopularMoviesViewModel(
    private val app: Application,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : AndroidViewModel(app) {
    fun getPopularMoviesList(): Flow<PagingData<MoviesModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PopularMoviePagingSource()
            }).flow
    }

    private val TMDB_STARTING_PAGE_INDEX = 1

    inner class PopularMoviePagingSource() : PagingSource<Int, MoviesModel>() {
        override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            return try {
                val response = getPopularMoviesUseCase.execute(

                    page = pageIndex
                )
                val movies = response.data?.moviesModels

                val nextKey =
                    if (movies!!.isEmpty()) {
                        null
                    } else {
                        // By default, initial load size = 3 * NETWORK PAGE SIZE
                        // ensure we're not requesting duplicating items at the 2nd request
                        pageIndex + (params.loadSize / 20)
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

    }
}
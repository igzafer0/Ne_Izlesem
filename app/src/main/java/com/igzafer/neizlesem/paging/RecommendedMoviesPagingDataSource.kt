package com.igzafer.neizlesem.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.igzafer.neizlesem.data.remote.NeIzlesemApi
import com.igzafer.neizlesem.data.remote.models.movie.MovieData
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class RecommendedMoviesPagingDataSource @Inject constructor(
    private val movieId: Int,
    private val apiService: NeIzlesemApi
) : PagingSource<Int, MovieData>() {
    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {
        val pageNumber = params.key ?: 1
        return try {
            val response = apiService.getRecommendedMovies(movieId = movieId, page = pageNumber)
            val data = response.results

            LoadResult.Page(
                data = data,
                prevKey = if (pageNumber == 1) null else pageNumber,
                nextKey = if (data.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            Timber.d(e.message.toString())
            LoadResult.Error(e)
        }
    }
}
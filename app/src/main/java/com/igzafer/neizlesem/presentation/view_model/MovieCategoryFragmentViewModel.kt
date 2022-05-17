package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.*
import com.igzafer.neizlesem.data.model.actor.ActorsModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.usecase.movies.DiscoverMoviesUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class MovieCategoryFragmentViewModel(
    private val app: Application,
    private val discoverMoviesUseCase: DiscoverMoviesUseCase,

    ) : AndroidViewModel(app) {

    fun discoverMovies(genres: Int): Flow<PagingData<MoviesModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                DiscoverMoviesPagingSource(genres)
            }).flow
    }


    private val TMDB_STARTING_PAGE_INDEX = 1

    inner class DiscoverMoviesPagingSource(private val genres: Int) :
        PagingSource<Int, MoviesModel>() {
        override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            try {
                val response = discoverMoviesUseCase.execute(page = pageIndex, genres)
                val actors = response.data?.moviesModels
                if (actors == null) {
                    return LoadResult.Error(Throwable("liste boş"))
                } else {
                    return try {
                        val nextKey =
                            if (actors.isEmpty()) {
                                null
                            } else {
                                // By default, initial load size = 3 * NETWORK PAGE SIZE
                                // ensure we're not requestxing duplicating items at the 2nd request
                                pageIndex + (params.loadSize / 20)
                            }
                        LoadResult.Page(
                            data = actors,
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
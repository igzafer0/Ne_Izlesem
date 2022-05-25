package com.igzafer.neizlesem.presentation.view_model.search_fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.paging.*
import com.igzafer.neizlesem.data.model.movie.MoviesModel
import com.igzafer.neizlesem.domain.usecase.movies.SearchMovieUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class SearchFragmentViewModel(
    private val app: Application,
    private val searchMovieUseCase: SearchMovieUseCase,

    ) : AndroidViewModel(app) {

    fun searchMovie(query: String): Flow<PagingData<MoviesModel>> {

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                SearchMoviePagingSource(query)
            }).flow
    }


    private val TMDB_STARTING_PAGE_INDEX = 1

    inner class SearchMoviePagingSource(val query: String) : PagingSource<Int, MoviesModel>() {
        override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
            return state.anchorPosition
        }


        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            try {
                val response = searchMovieUseCase.execute(query = query,page = pageIndex)
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
                                pageIndex + (2)
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
package com.igzafer.neizlesem.presentation.view_model.search_fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.*
import com.igzafer.neizlesem.data.model.actor.actor_details.ActorsModel
import com.igzafer.neizlesem.data.model.category.BaseCategoryModel
import com.igzafer.neizlesem.data.model.category.CategoryModel
import com.igzafer.neizlesem.domain.usecase.actors.GetPopularActorsUseCase
import com.igzafer.neizlesem.domain.usecase.categories.GetMovieCategoriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class SearchPageFragmentViewModel(
    private val app: Application,
    private val getPopularActorsUseCase: GetPopularActorsUseCase,
    private val getMovieCategoriesUseCase: GetMovieCategoriesUseCase
) : AndroidViewModel(app) {

    fun getPopularActors(): Flow<PagingData<ActorsModel>> {

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                PopularActorsPagingSource()
            }).flow
    }

    suspend fun getMovieCategories(): BaseCategoryModel =
        getMovieCategoriesUseCase.execute().data!!


    private val TMDB_STARTING_PAGE_INDEX = 1

    inner class PopularActorsPagingSource() : PagingSource<Int, ActorsModel>() {
        override fun getRefreshKey(state: PagingState<Int, ActorsModel>): Int? {
            return state.anchorPosition
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ActorsModel> {
            val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
            try {
                val response = getPopularActorsUseCase.execute(page = pageIndex)
                val actors = response.data?.actorsModels
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
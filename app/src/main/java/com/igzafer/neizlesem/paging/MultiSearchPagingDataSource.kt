package com.igzafer.neizlesem.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.igzafer.neizlesem.data.remote.NeIzlesemApi
import com.igzafer.neizlesem.data.remote.models.movie.MovieData
import com.igzafer.neizlesem.data.remote.models.search.SearchData
import com.igzafer.neizlesem.domain.model.CreditsModel
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class MultiSearchPagingDataSource @Inject constructor(
    private val query: String,
    private val apiService: NeIzlesemApi
) : PagingSource<Int, SearchData>() {
    override fun getRefreshKey(state: PagingState<Int, SearchData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchData> {
        val pageNumber = params.key ?: 1
        return try {
            val response = apiService.searchMultiData(page = pageNumber, query = query)
            val data = response.results!!
            val returnData = mutableListOf<SearchData>()
            for (i in data.indices){
                if(data[i].media_type!="tv"){
                    returnData.add(data[i])
                }
            }
            LoadResult.Page(
                data = returnData,
                prevKey = if (pageNumber == 1) null else pageNumber,
                nextKey = if (data.isEmpty()) null else pageNumber + 1
            )
        } catch (e: Exception) {
            Timber.d(e.message.toString())
            LoadResult.Error(e)
        }
    }
}
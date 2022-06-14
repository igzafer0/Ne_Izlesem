package com.igzafer.neizlesem.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.igzafer.neizlesem.data.remote.NeIzlesemApi
import com.igzafer.neizlesem.data.remote.models.search.SearchData
import com.igzafer.neizlesem.domain.repository.SearchRepository
import com.igzafer.neizlesem.paging.MultiSearchPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImpl @Inject constructor(private val apiService: NeIzlesemApi) : SearchRepository {
    override suspend fun searchMultiData(query: String): Flow<PagingData<SearchData>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { MultiSearchPagingDataSource(query, apiService) }
        ).flow
    }
}
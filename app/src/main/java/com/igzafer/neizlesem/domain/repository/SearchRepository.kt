package com.igzafer.neizlesem.domain.repository

import androidx.paging.PagingData
import com.igzafer.neizlesem.data.remote.models.movie.MovieData
import com.igzafer.neizlesem.data.remote.models.search.SearchData
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchMultiData(query: String): Flow<PagingData<SearchData>>
}
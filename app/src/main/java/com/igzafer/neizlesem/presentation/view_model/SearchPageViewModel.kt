package com.igzafer.neizlesem.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.igzafer.neizlesem.data.remote.models.search.toMultiSearchModel
import com.igzafer.neizlesem.domain.model.MultiSearchModel
import com.igzafer.neizlesem.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    val searchQuery = MutableLiveData<String>()

    init {
        searchQuery.value = ""
    }


    suspend fun searchData(query: String): Flow<PagingData<MultiSearchModel>> {
        val response = repository.searchMultiData(query).toMultiSearchModel()
        val returnData = mutableListOf<MultiSearchModel>()

        response.map { model ->
            model.map {
                if (it.mediaType != "tv") {

                    returnData.add(it)
                }
            }
        }
        return response

    }
}
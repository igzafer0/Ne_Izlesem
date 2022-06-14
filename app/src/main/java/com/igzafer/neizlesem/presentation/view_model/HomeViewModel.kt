package com.igzafer.neizlesem.presentation.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.igzafer.neizlesem.data.remote.models.movie.toMoviesModel
import com.igzafer.neizlesem.domain.model.HomeModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.igzafer.neizlesem.domain.model.toHomeModel
import com.igzafer.neizlesem.domain.repository.MovieRepository
import com.igzafer.neizlesem.presentation.adapter.HomePageAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    suspend fun getPopularMovies(): List<HomeModel> {
        val title: HomeModel = HomeModel(1, "Popüler filmler", null)
        val response = repository.getPopularMovies().results.toMoviesModel().toHomeModel(0, null)
        val result = mutableListOf<HomeModel>()
        result.addAll(listOf(title))
        result.addAll(response)
        return result

    }

}
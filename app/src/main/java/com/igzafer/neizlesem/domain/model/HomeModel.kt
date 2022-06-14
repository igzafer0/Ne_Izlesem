package com.igzafer.neizlesem.domain.model

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

data class HomeModel(
    val viewType: Int,
    val value: String?,
    val moviesModel: MoviesModel?
)
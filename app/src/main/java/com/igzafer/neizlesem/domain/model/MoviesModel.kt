package com.igzafer.neizlesem.domain.model

import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.Serializable


data class MoviesModel(
    val id: Int,
    val posterPath: String?,
    val title: String,
    val releaseDate: String?,
    val voteAverage: Double,
)

fun List<MoviesModel>.toHomeModel(
    viewType: Int,
    value: String?
): List<HomeModel> {
    return map { data ->
        HomeModel(
            viewType = viewType,
            value = value,
            moviesModel = data
        )
    }
}
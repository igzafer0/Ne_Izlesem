package com.igzafer.neizlesem.data.remote.models.search

import androidx.paging.PagingData
import androidx.paging.map
import com.igzafer.neizlesem.data.util.sozluk
import com.igzafer.neizlesem.domain.model.MultiSearchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class SearchData(
    val adult: Boolean?,
    val backdrop_path: String?,
    val first_air_date: String?,
    val gender: Int?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val known_for_department: String?,
    val media_type: String?,
    val name: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_name: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val profile_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun Flow<PagingData<SearchData>>.toMultiSearchModel(): Flow<PagingData<MultiSearchModel>> {
    return map { paging ->
        paging.map {
            MultiSearchModel(
                id = it.id!!,
                name = if (it.media_type == "movie") it.title else it.name,
                date = if (it.media_type == "movie") it.release_date else sozluk(it.known_for_department!!),
                department = if (it.media_type == "person") sozluk(it.known_for_department!!) else null,
                mediaType = it.media_type,
                picturePath = if (it.media_type == "person") it.profile_path else it.poster_path,
                voteAverage = if (it.media_type == "person") 0.0 else it.vote_average
            )
        }
    }
}


package com.igzafer.neizlesem.data.remote.models.people

import com.igzafer.neizlesem.data.util.sozluk
import com.igzafer.neizlesem.domain.model.PersonDetailModel

import timber.log.Timber
import java.util.ArrayList

data class PersonDetailData(
    val birthday: String?,
    val known_for_department: String?,
    val deathday: String?,
    val id: Int?,
    val name: String?,
    val gender: Int?,
    val biography: String?,
    val popularity: Double?,
    val place_of_birth: String?,
    val profile_path: String?,
    val adult: Boolean?,
    val imdb_id: String?,
    val homepage: String?
)

fun PersonDetailData.toPersonDetailModel(): PersonDetailModel {
    return PersonDetailModel(
        profilePath = profile_path ?: "",
        birthday = birthday ?: "",
        id = id!!,
        name = name!!,
        biography = if (biography != "") biography!! else "Bu kişi için bir biyografi bilgisi girilmemiş.",
        popularity = popularity!!,
        imdbId = imdb_id ?: "",
        gender = gender!!,
        knowForDepartment = sozluk(known_for_department!!)
    )
}


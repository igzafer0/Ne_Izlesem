package com.igzafer.neizlesem.data.remote.models.people

import com.igzafer.neizlesem.data.util.sozluk
import com.igzafer.neizlesem.domain.model.CreditsModel
import timber.log.Timber


data class CrewData(
    val adult: Boolean?,
    val credit_id: String?,
    val department: String?,
    val gender: Int?,
    val id: Int?,
    val job: String?,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?
)

fun List<CrewData>.toCreditsModel(): List<CreditsModel> {
    return map {
        CreditsModel(
            id = it.id!!,
            profilePath = it.profile_path,
            role = sozluk(it.job!!),
            name = it.name!!
        )
    }
}


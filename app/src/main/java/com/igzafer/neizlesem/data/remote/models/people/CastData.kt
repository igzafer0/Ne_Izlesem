package com.igzafer.neizlesem.data.remote.models.people

import com.igzafer.neizlesem.domain.model.CreditsModel

data class CastData(
    val adult: Boolean?,
    val cast_id: Int?,
    val character: String?,
    val credit_id: String?,
    val gender: Int?,
    val id: Int?,
    val known_for_department: String?,
    val name: String?,
    val order: Int?,
    val original_name: String?,
    val popularity: Double?,
    val profile_path: String?,
)

fun List<CastData>.toCreditsModel(): List<CreditsModel> {
    return map {
        CreditsModel(
            id = it.id!!,
            profilePath = it.profile_path,
            role = if (it.character!!.length > 10) it.character.substring(
                0,
                9
            ) + "..." else it.character,
            name = it.name!!,
        )
    }
}
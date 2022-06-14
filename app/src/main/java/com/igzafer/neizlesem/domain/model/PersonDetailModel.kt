package com.igzafer.neizlesem.domain.model

data class PersonDetailModel(
    val profilePath:String?,
    val birthday: String?,
    val id: Int?,
    val name: String?,
    val biography: String?,
    val popularity: Double?,
    val imdbId: String,
    val gender: Int,
    val knowForDepartment:String?
)
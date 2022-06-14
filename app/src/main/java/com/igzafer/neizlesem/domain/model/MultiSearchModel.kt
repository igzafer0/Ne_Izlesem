package com.igzafer.neizlesem.domain.model

data class MultiSearchModel(
    val id: Int,
    val name: String?,
    val picturePath: String?,
    val department: String?,
    val mediaType: String?,
    val date: String?,
    val voteAverage: Double?
)
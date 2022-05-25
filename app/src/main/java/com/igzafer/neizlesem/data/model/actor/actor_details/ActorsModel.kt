package com.igzafer.neizlesem.data.model.actor.actor_details


import com.google.gson.annotations.SerializedName

data class ActorsModel(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    @SerializedName("known_for")
    val knownFor: List<KnownFor>,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String?
)
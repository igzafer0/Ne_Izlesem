package com.igzafer.neizlesem.data.model.actor.actor_details


import com.google.gson.annotations.SerializedName

data class BaseActorModel(
    val page: Int,
    @SerializedName("results")
    val actorsModels: List<ActorsModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
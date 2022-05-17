package com.igzafer.neizlesem.data.model.category

import com.google.gson.annotations.SerializedName


data class BaseCategoryModel(
    @SerializedName("genres")
    val categoryModels: List<CategoryModel>
)
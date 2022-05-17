package com.igzafer.neizlesem.data.model.category


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryModel(
    val id: Int,
    val name: String
):Serializable
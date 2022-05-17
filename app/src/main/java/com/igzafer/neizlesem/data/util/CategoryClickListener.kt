package com.igzafer.neizlesem.data.util

import com.igzafer.neizlesem.data.model.category.CategoryModel
import com.igzafer.neizlesem.data.model.movie.MoviesModel

var onItemClickListenerCategory: ((CategoryModel) -> Unit)? = null
fun setOnClickItemListenerCategory(listener: (CategoryModel) -> Unit) {
    onItemClickListenerCategory = listener
}
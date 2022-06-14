package com.igzafer.neizlesem.util

class ItemClickListener(val clickListener: (model: Any) -> Unit) {
    fun onClick(model: Any) = clickListener(model)
}
package com.igzafer.neizlesem.data.remote.models.search

data class SearchDataObject(
    val page: Int?,
    val results: List<SearchData>?,
    val total_pages: Int?,
    val total_results: Int?
)
package com.igzafer.neizlesem.data.util

import timber.log.Timber

fun sozluk(key: String): String {
    val map = mapOf(
        "Director" to "Yönetmen",
        "Directing" to "Yönetmen",
        "Acting" to "Oyuncu",
        "Writing" to "Yazar",
        "Writer" to "Yazar"
    )
    return if (map[key] != null) {
        map[key]!!
    } else {
        key
    }

}
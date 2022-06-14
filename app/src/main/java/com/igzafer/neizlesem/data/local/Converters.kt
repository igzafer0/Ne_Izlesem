package com.igzafer.neizlesem.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.igzafer.neizlesem.data.remote.models.movie.MovieGenreObject
import com.igzafer.neizlesem.di.moshi
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    private val genreType =
        Types.newParameterizedType(List::class.java, MovieGenreObject::class.java)
    private val genreAdapter = moshi.adapter<List<MovieGenreObject>>(genreType)

    @TypeConverter
    fun fromMovieGenreObject(movieGenreObject: List<MovieGenreObject>): String {
        return genreAdapter.toJson(movieGenreObject)
    }

    @TypeConverter
    fun toMovieGenreObject(name: String): List<MovieGenreObject> {
        return genreAdapter.fromJson(name).orEmpty()

    }
}
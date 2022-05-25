package com.igzafer.neizlesem.data.database

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromSource(genre: List<Int>):List<Int>{
        return genre
    }
    @TypeConverter
    fun toSource(genre: List<Int>):List<Int>{
        return genre
    }
}
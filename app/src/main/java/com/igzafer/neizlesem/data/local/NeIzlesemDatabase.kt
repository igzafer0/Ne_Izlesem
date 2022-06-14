package com.igzafer.neizlesem.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.igzafer.neizlesem.domain.model.MovieDetailsModel
import com.igzafer.neizlesem.domain.model.MoviesModel
import com.squareup.moshi.Moshi

@Database(entities = [MovieDetailsModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NeIzlesemDatabase : RoomDatabase() {
    abstract val neIzlesemDao: NeIzlesemDao
}
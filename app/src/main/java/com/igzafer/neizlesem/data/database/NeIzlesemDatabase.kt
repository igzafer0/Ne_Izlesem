package com.igzafer.neizlesem.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.igzafer.neizlesem.data.model.movie.MoviesModel

@Database(
    entities = [MoviesModel::class],
    version = 1,
    exportSchema = false
)

abstract class NeIzlesemDatabase : RoomDatabase() {
    abstract fun getNeIzlesemDao():NeIzlesemDao
}
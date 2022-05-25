package com.igzafer.neizlesem.presentation.di

import android.app.Application
import androidx.room.Room
import com.igzafer.neizlesem.data.database.NeIzlesemDao
import com.igzafer.neizlesem.data.database.NeIzlesemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideNeIzlesemDatabase(app: Application): NeIzlesemDatabase {
        return Room.databaseBuilder(app, NeIzlesemDatabase::class.java, "ne_izlesem_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNeIzlesemDao(neIzlesemDatabase: NeIzlesemDatabase): NeIzlesemDao {
        return neIzlesemDatabase.getNeIzlesemDao()
    }
}
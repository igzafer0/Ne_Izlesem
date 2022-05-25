package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.data.api.ApiService
import com.igzafer.neizlesem.data.database.NeIzlesemDao
import com.igzafer.neizlesem.data.repository.data_source.MoviesLocalDataSource
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import com.igzafer.neizlesem.data.repository.data_source_impl.MoviesLocalDataSourceImpl
import com.igzafer.neizlesem.data.repository.data_source_impl.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideMoviesLocalDataSource(dao: NeIzlesemDao): MoviesLocalDataSource {
        return MoviesLocalDataSourceImpl(dao)
    }

}
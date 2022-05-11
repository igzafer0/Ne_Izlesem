package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.data.api.ApiService
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import com.igzafer.neizlesem.data.repository.data_source_impl.MoviesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(apiService: ApiService): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(apiService)
    }
}
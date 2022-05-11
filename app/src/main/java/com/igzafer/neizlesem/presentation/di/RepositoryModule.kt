package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.data.repository.MovieRepositoryImpl
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import com.igzafer.neizlesem.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(moviesRemoteDataSource)
    }
}
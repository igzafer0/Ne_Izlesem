package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.data.repository.ActorsRepositoryImpl
import com.igzafer.neizlesem.data.repository.CategoriesRepositoryImpl
import com.igzafer.neizlesem.data.repository.MovieRepositoryImpl
import com.igzafer.neizlesem.data.repository.data_source.ActorsRemoteDataSource
import com.igzafer.neizlesem.data.repository.data_source.CategoriesRemoteDataSource
import com.igzafer.neizlesem.data.repository.data_source.MoviesLocalDataSource
import com.igzafer.neizlesem.data.repository.data_source.MoviesRemoteDataSource
import com.igzafer.neizlesem.domain.repository.ActorRepository
import com.igzafer.neizlesem.domain.repository.CategoryRepository
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
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(moviesRemoteDataSource,moviesLocalDataSource)
    }
    @Singleton
    @Provides
    fun provideActorsRepository(
        actorsRemoteDataSource: ActorsRemoteDataSource
    ): ActorRepository {
        return ActorsRepositoryImpl(actorsRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideCategoriesRepository(
        categoriesRemoteDataSource: CategoriesRemoteDataSource
    ): CategoryRepository {
        return CategoriesRepositoryImpl(categoriesRemoteDataSource)
    }

}
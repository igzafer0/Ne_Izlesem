package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.domain.repository.MovieRepository
import com.igzafer.neizlesem.domain.usecase.GetNowPlayingMovieUseCase
import com.igzafer.neizlesem.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetPopularMoviesUseCase(
        movieRepository: MovieRepository
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideGetNowPlayingMoviesUseCase(
        movieRepository: MovieRepository
    ): GetNowPlayingMovieUseCase {
        return GetNowPlayingMovieUseCase(movieRepository)
    }
}
package com.igzafer.neizlesem.presentation.di

import android.app.Application
import com.igzafer.neizlesem.domain.usecase.GetNowPlayingMovieUseCase
import com.igzafer.neizlesem.domain.usecase.GetPopularMoviesUseCase
import com.igzafer.neizlesem.presentation.view_model.NowPlayingMoviesViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.PopularMoviesViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providePopularMoviesViewModelFactory(
        application: Application,
        getPopularMoviesUseCase: GetPopularMoviesUseCase
    ): PopularMoviesViewModelFactory {
        return PopularMoviesViewModelFactory(application, getPopularMoviesUseCase)
    }

    @Singleton
    @Provides
    fun provideNowPlayingMoviesViewModelFactory(
        application: Application,
        getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase
    ): NowPlayingMoviesViewModelFactory {
        return NowPlayingMoviesViewModelFactory(application, getNowPlayingMovieUseCase)
    }
}
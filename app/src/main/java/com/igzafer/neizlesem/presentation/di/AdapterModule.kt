package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.presentation.adapter.NowPlayingMoviesRowAdapter
import com.igzafer.neizlesem.presentation.adapter.PopularMoviesRowAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNowPlayingMoviesRowAdapter(): NowPlayingMoviesRowAdapter {
        return NowPlayingMoviesRowAdapter()
    }

    @Singleton
    @Provides
    fun providePopularMoviesRowAdapter(): PopularMoviesRowAdapter {
        return PopularMoviesRowAdapter()
    }
}
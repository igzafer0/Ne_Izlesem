package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.presentation.adapter.PopularMoviesAdapter
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
    fun providePopularMoviesAdapter(): PopularMoviesAdapter {
        return PopularMoviesAdapter()
    }
}
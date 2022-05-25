package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.presentation.adapter.Actors.CastRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Actors.PopularActorsRowAdapter
import com.igzafer.neizlesem.presentation.adapter.Category.MovieCategoryAdapter
import com.igzafer.neizlesem.presentation.adapter.Movie.*
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

    @Singleton
    @Provides
    fun provideUpcomingMoviesRowAdapter(): UpcomingMoviesAdapter {
        return UpcomingMoviesAdapter()
    }

    @Singleton
    @Provides
    fun provideTrendingWeeklyMoviesRowAdapter(): TrendingWeeklyMoviesAdapter {
        return TrendingWeeklyMoviesAdapter()
    }

    @Singleton
    @Provides
    fun provideGetPopularActorsRowAdapter(): PopularActorsRowAdapter {
        return PopularActorsRowAdapter()
    }

    @Singleton
    @Provides
    fun provideGetMovieCategoriesRowAdapter(): MovieCategoryAdapter {
        return MovieCategoryAdapter()
    }

    @Singleton
    @Provides
    fun provideDiscoverMoviesAdapter(): DiscoverMoviesAdapter {
        return DiscoverMoviesAdapter()
    }

    @Singleton
    @Provides
    fun provideSearchMoviesAdapter(): SearchMovieAdapter {
        return SearchMovieAdapter()
    }

    @Singleton
    @Provides
    fun provideGetCastAdapter(): CastRowAdapter {
        return CastRowAdapter()
    }

    @Singleton
    @Provides
    fun provideSavedMoviesAdapter(): SavedMoviesAdapter {
        return SavedMoviesAdapter()
    }
}
package com.igzafer.neizlesem.presentation.di

import android.app.Application
import com.igzafer.neizlesem.domain.usecase.actors.GetCastUseCase
import com.igzafer.neizlesem.domain.usecase.actors.GetPopularActorsUseCase
import com.igzafer.neizlesem.domain.usecase.categories.GetMovieCategoriesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.*
import com.igzafer.neizlesem.presentation.view_model.category_fragment.MovieCategoryFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.details_fragment.MovieDetailsFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.home_fragment.HomeFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.saved_fragment.SavedPageFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchFragmentViewModelFactory
import com.igzafer.neizlesem.presentation.view_model.search_fragment.SearchPageFragmentViewModelFactory
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
    fun provideHomeViewModelFactory(
        application: Application,
        getNowPlayingMovieUseCase: GetNowPlayingMovieUseCase,
        getPopularMoviesUseCase: GetPopularMoviesUseCase,
        getTrendingWeeklyMoviesUseCase: GetTrendingWeeklyMoviesUseCase,
        getUpcomingMovieUseCase: GetUpcomingMovieUseCase,
    ): HomeFragmentViewModelFactory {
        return HomeFragmentViewModelFactory(
            application, getNowPlayingMovieUseCase,
            getPopularMoviesUseCase,
            getTrendingWeeklyMoviesUseCase,
            getUpcomingMovieUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSearchViewModelFactory(
        application: Application,
        getPopularActorsUseCase: GetPopularActorsUseCase,
        getMovieCategoriesUseCase: GetMovieCategoriesUseCase
    ): SearchPageFragmentViewModelFactory {
        return SearchPageFragmentViewModelFactory(
            application, getPopularActorsUseCase, getMovieCategoriesUseCase
        )
    }

    @Singleton
    @Provides
    fun provideMovieCategoryFragmentViewModelFactory(
        application: Application,
        discoverMoviesUseCase: DiscoverMoviesUseCase
    ): MovieCategoryFragmentViewModelFactory {
        return MovieCategoryFragmentViewModelFactory(
            application, discoverMoviesUseCase
        )
    }

    @Singleton
    @Provides
    fun provideMovieDetailsFragmentViewModelFactory(
        application: Application,
        getMovieDetailsUseCase: GetMovieDetailsUseCase,
        deleteSavedMoviesUseCase: DeleteSavedMoviesUseCase,
        saveMoviesUseCase: SaveMoviesUseCase,
        getCastUseCase: GetCastUseCase,
        isExistMovieUseCase: IsExistMovieUseCase,
        getMoviesImagesUseCase: GetMovieImagesUseCase

    ): MovieDetailsFragmentViewModelFactory {
        return MovieDetailsFragmentViewModelFactory(
            application,
            getMovieDetailsUseCase,
            deleteSavedMoviesUseCase,
            saveMoviesUseCase,
            getCastUseCase,
            isExistMovieUseCase,
            getMoviesImagesUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSearchFragmentViewModelFactory(
        application: Application,
        searchMovieUseCase: SearchMovieUseCase
    ): SearchFragmentViewModelFactory {
        return SearchFragmentViewModelFactory(
            application, searchMovieUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSavedPageFragmentViewModelFactory(
        application: Application,
        getSavedMoviesUseCase: GetSavedMoviesUseCase,
    ): SavedPageFragmentViewModelFactory {
        return SavedPageFragmentViewModelFactory(
            application, getSavedMoviesUseCase
        )
    }
}
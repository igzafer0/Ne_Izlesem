package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.domain.repository.ActorRepository
import com.igzafer.neizlesem.domain.repository.CategoryRepository
import com.igzafer.neizlesem.domain.repository.MovieRepository
import com.igzafer.neizlesem.domain.usecase.actors.GetCastUseCase
import com.igzafer.neizlesem.domain.usecase.actors.GetPopularActorsUseCase
import com.igzafer.neizlesem.domain.usecase.categories.GetMovieCategoriesUseCase
import com.igzafer.neizlesem.domain.usecase.movies.*
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

    @Provides
    fun provideUpcomingMoviesUseCase(
        movieRepository: MovieRepository
    ): GetUpcomingMovieUseCase {
        return GetUpcomingMovieUseCase(movieRepository)
    }

    @Provides
    fun provideTrendingWeeklyMoviesUseCase(
        movieRepository: MovieRepository
    ): GetTrendingWeeklyMoviesUseCase {
        return GetTrendingWeeklyMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideGetPopularActorsUseCase(
        actorRepository: ActorRepository
    ): GetPopularActorsUseCase {
        return GetPopularActorsUseCase(actorRepository)
    }

    @Provides
    fun provideGetMovieCategoriesUseCase(
        categoryRepository: CategoryRepository
    ): GetMovieCategoriesUseCase {
        return GetMovieCategoriesUseCase(categoryRepository)
    }

    @Provides
    fun provideDiscoverMoviesUseCase(
        movieRepository: MovieRepository
    ): DiscoverMoviesUseCase {
        return DiscoverMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideMovieDetailsUseCase(
        movieRepository: MovieRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(movieRepository)
    }
    @Provides
    fun provideSearchMovieUseCase(
        movieRepository: MovieRepository
    ): SearchMovieUseCase {
        return SearchMovieUseCase(movieRepository)
    }
    @Provides
    fun provideGetCastUseCase(
        actorRepository: ActorRepository
    ): GetCastUseCase {
        return GetCastUseCase(actorRepository)
    }

    @Provides
    fun provideGetSavedMoviesUseCase(
        movieRepository: MovieRepository
    ): GetSavedMoviesUseCase {
        return GetSavedMoviesUseCase(movieRepository)
    }
    @Provides
    fun provideGetDeleteMovieUseCase(
        movieRepository: MovieRepository
    ): DeleteSavedMoviesUseCase {
        return DeleteSavedMoviesUseCase(movieRepository)
    }
    @Provides
    fun  provideSaveMoviesUseCase(
        movieRepository: MovieRepository
    ): SaveMoviesUseCase {
        return SaveMoviesUseCase(movieRepository)
    }

    @Provides
    fun  provideIsMovieExistUseCase(
        movieRepository: MovieRepository
    ): IsExistMovieUseCase {
        return IsExistMovieUseCase(movieRepository)
    }

    @Provides
    fun  provideGetMoviesImagesUseCase(
        movieRepository: MovieRepository
    ): GetMovieImagesUseCase {
        return GetMovieImagesUseCase(movieRepository)
    }
}
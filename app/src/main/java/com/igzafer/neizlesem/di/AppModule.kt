package com.igzafer.neizlesem.di

import android.app.Application
import androidx.room.Room
import com.igzafer.neizlesem.BuildConfig.BASE_URL
import com.igzafer.neizlesem.data.local.NeIzlesemDatabase
import com.igzafer.neizlesem.data.remote.NeIzlesemApi
import com.igzafer.neizlesem.data.repository.PeopleImpl
import com.igzafer.neizlesem.data.repository.MovieImpl
import com.igzafer.neizlesem.data.repository.SearchImpl
import com.igzafer.neizlesem.domain.repository.PeopleRepository
import com.igzafer.neizlesem.domain.repository.MovieRepository
import com.igzafer.neizlesem.domain.repository.SearchRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
        this.retryOnConnectionFailure(true)
        this.callTimeout(5, TimeUnit.MINUTES)
        this.connectTimeout(5, TimeUnit.MINUTES)
        this.readTimeout(5, TimeUnit.MINUTES)
        this.writeTimeout(5, TimeUnit.MINUTES)


    }.build()


    @Provides
    @Singleton
    fun provideNeIzlesemApi(): NeIzlesemApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(NeIzlesemApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        apiService: NeIzlesemApi,
        db: NeIzlesemDatabase
    ): MovieRepository {
        return MovieImpl(apiService, db.neIzlesemDao)
    }

    @Provides
    @Singleton
    fun provideActorsRepository(
        apiService: NeIzlesemApi,
    ): PeopleRepository {
        return PeopleImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        apiService: NeIzlesemApi,
    ): SearchRepository {
        return SearchImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideNeIzlesemDatabase(app: Application): NeIzlesemDatabase {
        return Room.databaseBuilder(app, NeIzlesemDatabase::class.java, "movies_database")
            .build()
    }
}
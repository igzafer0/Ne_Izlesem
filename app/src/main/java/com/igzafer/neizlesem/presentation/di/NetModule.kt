package com.igzafer.neizlesem.presentation.di

import com.igzafer.neizlesem.BuildConfig
import com.igzafer.neizlesem.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    val interceptor= HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
        this.retryOnConnectionFailure(true)
        this.callTimeout(5,TimeUnit.MINUTES)
        this.connectTimeout(5,TimeUnit.MINUTES)
        this.readTimeout(5,TimeUnit.MINUTES)
        this.writeTimeout(5,TimeUnit.MINUTES)


    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }
    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
}
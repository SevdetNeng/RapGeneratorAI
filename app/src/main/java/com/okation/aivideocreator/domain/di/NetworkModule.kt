package com.okation.aivideocreator.domain.di

import com.okation.aivideocreator.data.network.GptApi
import com.okation.aivideocreator.data.network.UberduckApi
import com.okation.aivideocreator.util.Constants
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
object NetworkModule {


    @Provides
    @Singleton
    fun provideInterceptor() : HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logger
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(logger : HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideGptApi(client : OkHttpClient) : GptApi {
        return Retrofit.Builder()
            .baseUrl(Constants.GPT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GptApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUberduckApi(client: OkHttpClient) : UberduckApi {
        return Retrofit.Builder()
            .baseUrl(Constants.UBERDUCK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(UberduckApi::class.java)
    }
}
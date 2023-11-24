package com.sevdetneng.rapgeneratorai.domain.di

import com.google.gson.Gson
import com.sevdetneng.rapgeneratorai.data.network.GptApi
import com.sevdetneng.rapgeneratorai.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
}
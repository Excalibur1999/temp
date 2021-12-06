package com.example.temp.di

import com.example.temp.Constants
import com.example.temp.network.ApiResponse
import com.example.temp.network.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideRetrofit():ApiResponse{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiResponse::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(api: ApiResponse):PostRepository{
        return PostRepository(api)
    }
}
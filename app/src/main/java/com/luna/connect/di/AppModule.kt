package com.luna.connect.di

import android.content.Context
import com.luna.connect.data.api.ApiClient
import com.luna.connect.data.api.ApiService
import com.luna.connect.data.local.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext context: Context): ApiService {
        return ApiClient.create(context).create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }
}
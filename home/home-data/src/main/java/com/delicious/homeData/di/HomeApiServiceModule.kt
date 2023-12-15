package com.delicious.homeData.di

import com.delicious.homeData.apiService.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
object HomeApiServiceModule {

    @Provides
    fun provideHomeApiService(retrofit: Retrofit): HomeApiService =
        retrofit.create(HomeApiService::class.java)

}